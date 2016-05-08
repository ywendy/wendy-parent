package com.wendy.algorithm.hash;

/**
 * hashing (散列算法)
 * 
 * @author tony
 *
 */
public class Hash01 {

	/**
	 * 散列函数1
	 * 
	 * 
	 * 简单的散列函数，实现方法： 把字符串中字符的ASCII(或UNICODE 码)值加起来。<br>
	 * 
	 * 优点：实现简单，并且可以很快的计算出结果<br>
	 * 缺点：如果表（tableSize ）很大，此函数不能很好的分配关键字。<br>
	 * 
	 * EG: 设tableSize = 10 007（10007
	 * 是一个素数），同时设，所有的关键字key最多为8个字符长，由于ASCII字符的值最多为127，因此散列函数的值hashVal只能在 0~1016
	 * 之间， 其中1016 = 127 *8 ，显然就不是一种均匀的分配了。
	 * 
	 * 
	 * 
	 * 
	 * @param key
	 * @param tableSize
	 * @return
	 */
	public static int hash1(String key, int tableSize) {
		int hashVal = 0;
		for (int i = 0; i < key.length(); i++) {
			hashVal += key.charAt(i);
		}
		return hashVal % tableSize;
	}

	/**
	 * 散列函数2
	 * 
	 * 计算公式：
	 * 
	 * keySize-1 <br>
	 * Σ Key[KeySize-i-1]*37^i = k0*37^0 + k1*37^1 +k2*37^2+.... <br>
	 * i=0 <br>
	 * 
	 * 借助Horner (霍纳法则) hk = (37*(k2)+k1)*37 + k0 <br>
	 * 
	 * @param key
	 * @param tableSize
	 * @return
	 */
	public static int hash2(String key, int tableSize) {
		int hashVal = 0;
		String s = "";
		for (int i = 0; i < key.length(); i++) {
			int t1 = 37 * hashVal;
			int t2 = key.charAt(i);
			s += " 37*" + hashVal + " + [k(" + i + ")" + t2 + "] +";
			hashVal = t1 + t2;
		}
		System.out.println(s);
		hashVal %= tableSize;
		if (hashVal < 0) {
			hashVal += tableSize;
		}

		return hashVal;
	}

	public static void main(String[] args) {
		int tableSize = 10007;
		for (int i = 0; i < 10; i++) {
			hash2("yaojian" + i, tableSize);
		}

	}

}
