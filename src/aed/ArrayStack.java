package aed;

public class ArrayStack implements Stack {
	
	private int[] array;
	private int size;
	
	public ArrayStack() {
		this.array = new int[10];
		this.size = 0;
	}
	
	private void duplicateArray() {
		int newArraySize = this.array.length * 2;
		int[] newArray = new int[newArraySize];
		for(int i = 0; i < this.array.length; i++) {
			newArray[i] = this.array[i];
		}
		this.array = newArray;
	}

	public void push(int element) {
		if(this.size >= this.array.length) {
			duplicateArray();
		}
		if(this.size == 0) {
			this.array[this.size] = element;
		} else {
			for(int i = this.size-1; i >= 0; i--) {
				this.array[i+1] = this.array[i];
			}
			this.array[0] = (int) element;
		}
		this.size++;
	}

	public int peek() {
		if(this.size == 0) {
			throw new RuntimeException("Empty stack");
		}
		return this.array[0];
	}

	public int pop() {
		if(this.size == 0) {
			throw new RuntimeException("Empty stack");
		}
		int save = this.array[0];
		for(int i = 0; i < this.size-1; i++) {
			this.array[i] = this.array[i+1];
		}
		this.size--;
		return save;
	}

	public int getSize() {
		return this.size;
	}

}
