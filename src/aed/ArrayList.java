package aed;

public class ArrayList implements List {
	
	private int[] array;
	private int size;

	public ArrayList() {
		this.array = new int[10];
		this.size = 0;
	}
	
	public int get(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		return this.array[index];
	}
	
	public void add(int element) {
		if(this.size >= this.array.length) {
			duplicateArray();
		}
		this.array[this.size] = (int) element;
		this.size++;
	}
	
	public void add(int element, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		if(this.size >= this.array.length) {
			duplicateArray();
		}
		for(int i = this.size-1; i >= index; i--) {
				this.array[i+1] = this.array[i];
		}
		this.array[index] = (int) element;
		this.size++;
	}
	
	public void duplicateArray() {
		int newArraySize = this.array.length * 2;
		int[] newArray = new int[newArraySize];
		for(int i = 0; i < this.array.length; i++) {
			newArray[i] = this.array[i];
		}
		this.array = newArray;
	}

	public void remove(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		for(int i = index; i < this.size-1; i++) {
			this.array[i] = this.array[i+1];
		}
		this.size--;
	}

	public int getSize() {
		return this.size;
	}
	
	public int indexOf(int element) {
		int count = 0;
		for(int i = 0; i < this.size; i++) {
			if(element == this.array[i]) {
				return count;
			}
			count++;
		}
		throw new RuntimeException("Element not found");
	}
	
	public String toString() {
		String string = "[";
		for (int i = 0; i < this.size; i++) {
			string += this.array[i] + ",";
		}
		return string += "]";
	}
	
}
