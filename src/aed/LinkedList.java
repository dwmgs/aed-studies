package aed;

public class LinkedList implements List{
	
	private Node head;
	private Node tail;
	private int size;

	public int get(int index) {
		Node node = this.getInternal(index);
		if(node != null) {
			return node.getValue();
		}
		throw new RuntimeException("Invalid Index");
	}
	
	private Node getInternal(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		
		int currentIndex = 0;
		for(Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
			if(currentIndex == index) {
				return currentNode;
			}
			currentIndex++;
		}
		throw new RuntimeException("Invalid Index");
	}
	
	
	public void add(int element) {
		Node newNode = new Node(element);
		if(this.size == 0) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		this.size++;
	}

	public void add(int element, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		Node newNode = new Node(element);
		if(index == 0) {
			newNode.setNext(head);
			this.head = newNode;
			this.size++;
		} else if(index == this.size) {
			this.add(element);
		} else {
			Node previous = this.getInternal(index - 1);
			Node nexter = previous.getNext();
			previous.setNext(newNode);
			newNode.setNext(nexter);
			this.size++;
		}
	}

	public void remove(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		if(index == 0) {
			this.head = this.head.getNext();
		} else if(index == this.size-1) {
			Node previous = this.getInternal(index - 1);
			this.tail = previous;
			this.tail.setNext(null);
		} else {
			Node previous = this.getInternal(index - 1);
			Node nexter = this.getInternal(index).getNext();
			previous.setNext(nexter);
		}
		this.size--;
	}

	public int getSize() {
		return this.size;
	}
	
	public void reverse() {
		Node previous = null;
		Node current = this.head;
		Node next;
		for(int i = 0; i < this.size; i++) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next; 
		}
		this.tail = this.head;
		this.head = previous;
	}
	
	public void addSorted(int element) {
		Node newNode = new Node(element);
		Node previous = null;
		Node next = null;
		if(this.size == 0) {
			this.add(element);
		}else {
			for(Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
				if(element > this.tail.getValue()) {
					this.add(element);
				} else if(element < this.head.getValue()) {
					newNode.setNext(this.head);
					this.head = newNode;
					this.size++;
				} else if(element > currentNode.getValue() && element < currentNode.getNext().getValue()) {
					previous = currentNode;
					next = currentNode.getNext();
					previous.setNext(newNode);
					newNode.setNext(next);
					this.size++;
				}	
			}
		}
	}	
	
	public void swap(int indexOne, int indexTwo) {
		int one = this.getInternal(indexOne).getValue();
		int two = this.getInternal(indexTwo).getValue();
		Node oneNode = this.getInternal(indexOne);
		Node twoNode = this.getInternal(indexTwo);
		oneNode.setValue(two);
		twoNode.setValue(one);
	}
		
	public String toString() {
		String string = "[";
		for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
			string += currentNode.getValue() + ", ";
		}
		return string += "]";
	}
	
	private class Node{
		
		private int value;
		private Node next;
		
		public Node(Object element) {
			this.value = (int) element;
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
		
		public void setValue(int element) {
			this.value = element;
		}
	}

}
