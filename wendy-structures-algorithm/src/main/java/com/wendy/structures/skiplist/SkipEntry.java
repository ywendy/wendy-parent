package com.wendy.structures.skiplist;

/**
 * 跳跃表.
 * 
 * @author tony
 *
 */
public class SkipEntry {

	public String key;
	public Integer value;

	public int pos; // 主要为了打印 链表用

	public SkipEntry up, down, left, right;

	public static String plusInfinity = "+∞";

	public static String minusInfinity = "-∞";

	public SkipEntry(String key, Integer value) {
		this.key = key;
		this.value = value;
		this.up = this.down = this.left = this.right = null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public Integer setValue(Integer val) {

		Integer oldValue = value;

		value = val;

		return oldValue;

	}

	public boolean equals(Object o) {

		SkipEntry ent;

		try {

			ent = (SkipEntry) o; // 检测类型

		} catch (ClassCastException ex) {

			return false;

		}

		return (ent.getKey() == key) && (ent.getValue() == value);

	}

	public String toString() {

		return "(" + key + "," + value + ")";

	}

}
