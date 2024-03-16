package aed;

public class LinkedStack implements Stack {
	
	private Node top;
	private int size;

	public void push(int element) {
		Node newNode = new Node(element);
		if(this.size == 0) {
			this.top = newNode;
		} else {
			newNode.setNext(this.top);
			this.top = newNode;
		}
		this.size++;
	}

	public int peek() {
		if(this.size == 0) {
			throw new RuntimeException("Empty Stack");
		}
		return this.top.getValue();
	}

	public int pop() {
		if(this.size == 0) {
			throw new RuntimeException("Empty Stack");
		}
		Node save = this.top;
		this.top = this.top.getNext();
		this.size--;
		return save.getValue();
	}

	public int getSize() {
		return this.size;
	}
	
	private class Node {
		
		private int value;
		private Node next;
		
		public Node(int element) {
			this.value = element;
			this.next = null;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public int getValue() {
			return value;
		}
		
	}
	
}
