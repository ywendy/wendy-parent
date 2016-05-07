package com.wendy.algorithm.simple;

/**
 * 取模运算 a mod b = a%b a%b 运算步骤： c = a/b(取余数); a%b = a - c*b;
 * 
 * @author tony
 *
 */
public class ModuloOperation {

	public static long mod(long a, long b) {
		long c = a / b;
		return a - c * b;
	}

	public static void main(String[] args) {
		long a = 9, b = 5;
		System.out.println(" a%b     = " + a % b);
		System.out.println(" a mod b = " + mod(a, b));
	}

}
