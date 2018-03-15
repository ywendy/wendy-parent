package com.wendy.algorithm.mix;

/**
 * 1,2,3,5,8
 * 3,5,6,7,9,20,56,8
 * diff
 * 3,5,8,2,1
 * 3,5,8,7,9,20,56,6
 * <p>
 * <p>
 * 2018/3/15.
 */
public class DifferenceSet {


    /**
     * 重复越多效率越高.
     *
     * @param source1
     * @param source2
     * @param <T>
     * @return 差异化索引值
     */

    public static <T> int diff(T[] source1, T[] source2) {

        int len1 = source1.length;
        int len2 = source2.length;
        int end = len1;

        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        boolean swap;
        for (int i = 0; i < end; ) {

            swap = false;

            for (int j = i; j < len2; j++) {
                if (source1[i].equals(source2[j])) {
                    T tmp = source2[i];
                    source2[i] = source2[j];
                    source2[j] = tmp;
                    swap = true;
                    break;
                }
            }

            if (!swap) {
                T tmp = source1[i];
                source1[i] = source1[--end];
                source1[end] = tmp;
            } else {
                i++;
            }

        }
        return end;
    }

    public static void main(String[] args) {
        Integer[] s1 = {1, 2, 3, 5, 8};
        Integer[] s2 = {3, 5, 6, 7, 9, 20, 56, 8};

        int end = diff(s1, s2);


        System.out.print("s1 diff:");
        for (int i = end; i < s1.length; i++) {
            System.out.print(s1[i] + ",");
        }
        System.out.println();


        System.out.print("s2 diff:");
        for (int i = end; i < s2.length; i++) {
            System.out.print(s2[i] + ",");
        }


    }

}
