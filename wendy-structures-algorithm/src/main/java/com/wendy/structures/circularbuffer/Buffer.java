package com.wendy.structures.circularbuffer;

public interface Buffer<T> {
	
	
	public T get();
	public int size();
	public void add(T t);

}
