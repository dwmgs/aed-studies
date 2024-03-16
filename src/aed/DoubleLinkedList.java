package aed;

public class DoubleLinkedList implements List {
	
	private Node head;
	private Node tail;
	private int size;

	public int get(int index) {
		Node node = this.getInternal(index);
		if(node != null) {
			return node.getValue();
		}
		throw new RuntimeException("Invalid index");
	}
	
	private Node getInternal(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		
		int currentIndexHead = 0;
		int currentIndexTail = this.size - 1;
		int mid = (this.size / 2);
		if(index <= mid) {
			for(Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
				if(currentIndexHead == index) {
					return currentNode;
				}
				currentIndexHead++;
			}
		} else if(index > mid){
			for(Node currentNode = this.tail; currentNode != null; currentNode = currentNode.getPrevious()) {
				if(currentIndexTail == index) {
					return currentNode;
				}
				currentIndexTail--;
			}
		}
		
		
		throw new RuntimeException("Index not found");
	}

	public void add(int element) {
		Node newNode = new Node(element);
		if(this.size == 0) {
			this.head = newNode;
			this.tail  = newNode;
		} else {
			newNode.setPrevious(this.tail);
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		this.size++;
	}

	public void add(int element, int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index");
		}
		
		Node newNode = new Node(element);
		if(index == 0) {
			if(this.size == 0) {
				this.add(element);
			} else {
				this.head.setPrevious(newNode);
				newNode.setNext(this.head);
				this.head = newNode;
				this.size++;
			}
		} else if(index == this.size) {
			this.add(element);
		} else {
			Node newPrevious = this.getInternal(index - 1);
			Node newNext = newPrevious.getNext();
			newPrevious.setNext(newNode);
			newNode.setPrevious(newPrevious);
			newNode.setNext(newNext);
			newNext.setPrevious(newNode);
			this.size++;
		}
	}

	public void remove(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invelid Index");
		}
		
		if(index == 0) {
			this.head = this.head.getNext();
			this.head.setPrevious(null);
			this.size--;
		} else if(index == this.size - 1) {
			this.tail = this.tail.getPrevious();
			this.tail.setNext(null);
			this.size--;
		} else {
			Node oldPrevious = this.getInternal(index - 1);
			Node oldNext = this.getInternal(index + 1);
			oldPrevious.setNext(oldNext);
			oldNext.setPrevious(oldPrevious);
			this.size--;
		}
	}

	public int getSize() {
		return this.size;
	}
	
	public int indexOf(int element) {
		return this.getElement(element);
	}
	
	private int getElement(int element) {
		int count = 0;
		for(Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
			if(element == currentNode.getValue()) {
				return count;
			}
			count++;
		}
		
		throw new RuntimeException("Element not found");
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
		private Node previous;
		
		public Node(int element) {
			this.value = element;
			this.next = null;
			this.previous = null;
		}
		
		public int getValue() {
			return value;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}
		
		public Node getPrevious() {
			return previous;
		}
		
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
		
	}
	
}
