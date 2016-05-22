package com.wendy.structures.circularbuffer;

import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * A lock-free thread safe circular fixed length buffer.
 * 
 * Use an AtomicLong as index counter and AtomicReferenceArray to hold the
 * references to the value.
 * 
 * When the buffer is full ,the oldest item is overwritten.
 * 
 * 
 * @author yaojian
 *
 * @param <T>
 */
public class CircularBuffer<T> {

	private final AtomicLong index = new AtomicLong(0l);
	private final AtomicReferenceArray<T> buffer;
	private final int size;

	public CircularBuffer(int size) {
		this.size = size;
		buffer = new AtomicReferenceArray<>(this.size);
	}

	public int size() {
		return size;
	}

	public void add(T item) {
		buffer.set((int) (index.incrementAndGet() % size), item);
	}

	public T get(long index) {
		return buffer.get((int) (index % size));
	}

	public T take(AtomicLong idx) {
		if (idx.get() >= index.get()) {
			return null;
		}
		if (index.get() - idx.get() > size) {
			idx.lazySet(index.get());
			throw new BufferOverflowException();
		}

		return get(idx.get());
	}

	public List<T> drain(AtomicLong idx) {
		if (idx.get() - index.get() > size) {// 套了一圈了
			idx.set(index.get());
			throw new BufferOverflowException();
		}
		List<T> result = new ArrayList<>();

		while (idx.get() < index.get()) {// 把当前index 后面的返回.
			result.add(get(idx.getAndIncrement()));
		}
		return result;

	}

	public AtomicLong index() {
		return new AtomicLong(index.get());
	}

}
