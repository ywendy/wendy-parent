package com.wendy.structures.circularbuffer;

public class CircularBufferTest {

	/*@Test
	public void emptyShouldReturnNull() {
		CircularBuffer<Integer> buf = new CircularBuffer<>(10);
		AtomicLong idx = buf.index();
		assertThat(buf.take(idx), nullValue());
	}

	@Test
	public void emptyShouldDrainEmptyList() {
		CircularBuffer<Integer> buf = new CircularBuffer<>(10);
		AtomicLong idx = buf.index();
		List<Integer> result = buf.drain(idx);
		assertThat(result.size(), is(0));
	}
	
	@Test
	public void twoDrainsYeildPartialResults(){
		
		CircularBuffer<Integer> buf = new CircularBuffer<>(10);
		
		AtomicLong idx = buf.index();
		for (int i = 0; i < 10; i++) {
			buf.add(i);
		}
		
		List<Integer> first = buf.drain(idx);
		assertThat(first, equalTo(Arrays.asList(0,1,2,3,4,5,6,7,8,9)));
		for (int i = 10; i < 20; i++) {
			buf.add(i);
		}
		
		
	}*/
	
	

}
