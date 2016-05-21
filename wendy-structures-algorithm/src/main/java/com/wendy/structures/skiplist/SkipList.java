package com.wendy.structures.skiplist;

import java.util.Random;

public class SkipList {

	public SkipEntry head;// 跳跃表头
	public SkipEntry tail;// 跳跃表尾

	public int n;// 跳跃表元素个数
	public int h;// 跳跃表高度

	public final Random random = new Random();// 随机数种子,用来投掷硬币

	public SkipList() {

		SkipEntry p1, p2;
		p1 = new SkipEntry(SkipEntry.minusInfinity, null);
		p2 = new SkipEntry(SkipEntry.plusInfinity, null);

		this.head = p1;
		this.tail = p2;

		p1.right = p2;
		p2.left = p1;
		n = 0;
		h = 0;

	}

	public SkipEntry findEntry(String k) {

		SkipEntry p;

		p = head;

		while (true) {

			/**
			 * 
			 * 一直向右找，例: k=34.
			 * 
			 * 10 ---> 20 ---> 30 ---> 40 ^ | p 会在30处停止
			 * 
			 * --------------------------------------------
			 * 
			 ***/

			while (p.right.key != SkipEntry.plusInfinity

					&& p.right.key.compareTo(k) <= 0) {

				p = p.right;

				// System.out.println(">>>> " + p.key);

			}

			// 如果还有下一层，就到下一层继续查找

			if (p.down != null) {

				p = p.down;

				// System.out.println("vvvv " + p.key);

			} else

				break; // 到了最下面一层 就停止查找

		}

		return (p); // p.key <= k

	}

	public Integer get(String key) {
		SkipEntry p = findEntry(key);
		if (p.key.equals(key)) {
			return p.value;
		}

		return null;
	}

	/** 放一个key-value到跳跃表中, 替换原有的并返回 */

	public Integer put(String k, Integer v) {

		SkipEntry p, q;

		int i;

		p = findEntry(k);

		if (k.equals(p.getKey())) {

			Integer old = p.value;

			p.value = v;

			return (old);

		}

		q = new SkipEntry(k, v);

		q.left = p;

		q.right = p.right;

		p.right.left = q;

		p.right = q;

		i = 0; // 当前层 level = 0

		while (random.nextDouble() < 0.5) {

			// 如果超出了高度，需要重新建一个顶层

			if (i >= h) {

				SkipEntry p1, p2;

				h = h + 1;

				p1 = new SkipEntry(SkipEntry.minusInfinity, null);

				p2 = new SkipEntry(SkipEntry.plusInfinity, null);

				p1.right = p2;

				p1.down = head;

				p2.left = p1;

				p2.down = tail;

				head.up = p1;

				tail.up = p2;

				head = p1;

				tail = p2;

			}

			while (p.up == null) {

				p = p.left;

			}

			p = p.up;

			SkipEntry e;

			e = new SkipEntry(k, null);

			e.left = p;

			e.right = p.right;

			e.down = q;

			p.right.left = e;

			p.right = e;

			q.up = e;

			q = e; // q 进行下一层迭代

			i = i + 1; // 当前层 +1

		}

		n = n + 1;

		return (null); // No old value

	}

	public Integer remove(String key) {

		return (null);

	}

	public void printHorizontal() {

		String s = "";

		int i;

		SkipEntry p;

		p = head;

		while (p.down != null) {

			p = p.down;

		}

		i = 0;

		while (p != null) {

			p.pos = i++;

			p = p.right;

		}

		p = head;

		while (p != null) {

			s = getOneRow(p);

			System.out.println(s);

			p = p.down;

		}

	}

	// 用了打印测试

	public String getOneRow(SkipEntry p) {

		String s;

		int a, b, i;

		a = 0;

		s = "" + p.key;

		p = p.right;

		while (p != null) {

			SkipEntry q;

			q = p;

			while (q.down != null)

				q = q.down;

			b = q.pos;

			s = s + " <-";

			for (i = a + 1; i < b; i++)

				s = s + "--------";

			s = s + "> " + p.key;

			a = b;

			p = p.right;

		}

		return (s);

	}

	// 用了打印测试

	public void printVertical() {

		String s = "";

		SkipEntry p;

		p = head;

		while (p.down != null)

			p = p.down;

		while (p != null) {

			s = getOneColumn(p);

			System.out.println(s);

			p = p.right;

		}

	}

	// 用了打印测试

	public String getOneColumn(SkipEntry p) {

		String s = "";

		while (p != null) {

			s = s + " " + p.key;

			p = p.up;

		}

		return (s);

	}

}
