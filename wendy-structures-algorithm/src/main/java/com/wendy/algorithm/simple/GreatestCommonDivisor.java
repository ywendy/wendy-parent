package com.wendy.algorithm.simple;

/**
 * 最大公约数（最大公因子）
 * 
 * 定义：两个或者多个整数共有约数中最大的一个.
 * 
 * 欧几里得算法（辗转相除法）：gcd(a,b) = gcd(b,a mod b)
 * 
 * @author tony
 *
 */
public class GreatestCommonDivisor {

	public static long gcd(long a, long b) {

		while (b != 0) {
			long tmp = a % b;
			a = b;
			b = tmp;
			System.out.println(" gcd method : a = " + a + ", b=" + b + ", tmp=" + tmp);
		}

		return a;
	}

	public static long recuseGcd(long a, long b) {
		if (b == 0) {
			System.out.println(" recuseGcd method : a = " + a + " , b = " + b);
			return a;
		}

		if (a == 0) {
			System.out.println(" recuseGcd method : a = " + a + " , b = " + b);
			return b;
		}

		// 交换两个数的位置
		if (a < b) {
			a ^= b;
			b ^= a;
			a ^= b;
		}

		System.out.println(" recuseGcd method : a = " + a + " , b = " + b);
		return recuseGcd(b, a % b);
	}

	public static void main(String[] args) {
		long a = 0, b = -1;
		String result = String.format("gcd(%d,%d) method result : %d", a, b, gcd(a, b));
		System.out.println(result);
		result = String.format("recuseGcd(%d,%d) method result : %d", a, b, recuseGcd(a, b));
		System.out.println(result);
	}

}
