package aed;

public class LinkedQueue implements Queue {
	
	private Node front;
	private Node rear;
	private int size;

	public void offer(int element) {
		Node newNode = new Node(element);
		if(this.size == 0) {
			this.front = newNode;
			this.rear = this.front;
		} else {
			this.rear.setNext(newNode);
			this.rear = newNode;
		}
		this.size++;
	}

	public int peek() {
		if(this.size == 0) {
			throw new RuntimeException("Empty Queue");
		}
		return this.front.getValue();
	}

	public int poll() {
		if(this.size == 0) {
			throw new RuntimeException("Empty Queue");
		}
		Node save = this.front;
		this.front = this.front.getNext();
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
