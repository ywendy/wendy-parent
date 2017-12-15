package com.wendy.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 计算矩阵中每行数据的总和，然后把总和相加
 * eg：
 * <p>
 * |——         ——|
 * |  1   2   5  |
 * |  3   9   7  |
 * |  8   6   6  |
 * |——         ——|
 * <p>
 * row1 = 1+2+5 = 8
 * row2 = 3+9+7 = 19
 * row3 = 8+6+6 = 30
 * <p>
 * sum = row1+row2+row3 = 8+19+30 = 57
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 构造的矩阵如下:
 * |  9  3  1  8  9  |
 * |  3  3  1  3  7  |
 * |  3  6  2  5  7  |
 * |  5  1  9  5  5  |
 * |  8  7  0  7  8  |
 * sum of 0 row: 30
 * sum of 1 row: 17
 * sum of 2 row: 23
 * sum of 3 row: 25
 * sum of 4 row: 30
 * Sum of rows of matrix :125
 * <p>
 * <p>
 * <p>
 * 2017/12/15.
 */
public class CyclicBarrierTest1 {


    public static void main(String[] args) {

        MatrixMoc m = new MatrixMoc(5, 5);

        int[][] matrix = m.getData();
        new SumThread().solver(matrix);
    }

}


/**
 * 矩阵定义
 */
class MatrixMoc {
    private int[][] data;

    private int size;//行数
    private int length;//每行元素的个数

    public MatrixMoc(int size, int length) {
        this.size = size;
        this.length = length;
        initMatrix();
    }

    private void initMatrix() {
        System.out.println("构造的矩阵如下:");
        data = new int[size][length];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (j == length - 1) {
                    System.out.print("  " + data[i][j] + "  ");
                } else {

                    System.out.print("  " + data[i][j]);
                }
            }
            System.out.print("|");
            System.out.println();
        }

    }

    /**
     * 获取矩阵数组.
     *
     * @return
     */
    public int[][] getData() {
        return data;
    }


}

class SumThread {
    CyclicBarrier sumBarrier;
    int[][] matrix;
    int N = 0;
    List<Integer> sumCarryList = new ArrayList<>();

    class Worker implements Runnable {

        int row;

        public Worker(int row) {
            this.row = row;
        }

        @Override
        public void run() {
            sumRow(row);
            try {
                sumBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
            }
        }
    }


    public void sumRow(int start) {
        int s = 0;
        for (int i = 0; i < matrix[start].length; i++) {
            s += matrix[start][i];
        }
        System.out.println("sum of " + start + " row: " + s);
        sumCarryList.add(s);
    }

    public void solver(int[][] matrix) {

        this.matrix = matrix;
        N = matrix.length;
        sumBarrier = new CyclicBarrier(N, new Runnable() {
            int sumTemp;

            @Override
            public void run() {
                for (int i = 0; i < sumCarryList.size(); i++) {
                    sumTemp += sumCarryList.get(i);
                }
                System.out.println("Sum of rows of matrix :" + sumTemp);
            }
        });

        for (int i = 0; i < N; i++) {
            new Thread(new Worker(i)).start();
        }

    }
}

















