package aed;

public class CircularList implements List {
	
	private Node head;
	private Node tail;
	private int size;

	public int get(int index) {
		Node node = this.getInternal(index);
		if(node != null) {
			return node.getValue();
		}
		throw new RuntimeException("Index not found");
	}
	
	private Node getInternal(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid Index");
		}
		
		int currentIndex = 0;
		for(Node currentNode = this.head; currentIndex < this.size; currentNode = currentNode.getNext()) {
			if(currentIndex == index) {
				return currentNode;
			}
			currentIndex++;
		}
		
		throw new RuntimeException("Index not found");
	}

	public void add(int element) {
		Node newNode = new Node(element);
		if(this.size == 0) {
			this.head = newNode;
			this.tail = newNode;
			this.tail.setNext(this.head);
			this.size++;
		} else {
			this.tail.setNext(newNode);
			this.tail = newNode;
			this.tail.setNext(this.head);
			this.size++;
		}
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
				newNode.setNext(this.head);
				this.head = newNode;
				this.tail.setNext(this.head);
				this.size++;
			}
		} else if(index == this.size) {
			this.add(element);
		} else {
			Node newPrevious = this.getInternal(index - 1);
			Node newNext = newPrevious.getNext();
			newPrevious.setNext(newNode);
			newNode.setNext(newNext);
			this.size++;
		}
	}

	public void remove(int index) {
		if(index < 0 || index > this.size) {
			throw new IllegalArgumentException("Invalid index");
		}
		
		if(index == 0) {
			this.head = this.head.getNext();
			this.tail.setNext(this.head);
			this.size--;
		} else if (index == this.size - 1) {
			Node previousTail = this.getInternal(index - 1);
			this.tail = previousTail;
			this.tail.setNext(this.head);
			this.size--;
		} else {
			Node oldPrevious = this.getInternal(index - 1); 
			Node oldNext = this.getInternal(index).getNext();
			oldPrevious.setNext(oldNext);
			this.size--;
		}
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
		this.tail.setNext(this.head);
	}
	
	public void addSorted(int element) {
		Node newNode = new Node(element);
		Node newPrevious = null;
		Node newNext = null;
		
		if(this.size == 0) {
			this.add(element);
		} else {
			for(Node currentNode = this.head; currentNode.getNext() != this.head; currentNode = currentNode.getNext()) {
				if(element > this.tail.getValue()) {
					this.add(element);
				} else if(element < this.head.getValue()) {
					newNode.setNext(this.head);
					this.head = newNode;
					this.tail.setNext(this.head);
					this.size++;
				} else if (element > currentNode.getValue() && element < currentNode.getNext().getValue()) {
					newPrevious = currentNode;
					newNext = newPrevious.getNext();
					newPrevious.setNext(newNode);
					newNode.setNext(newNext);
					this.size++;
				}
			}
		}
	}
	
	public void swap(int indexOne, int indexTwo) {
		Node one = this.getInternal(indexOne);
		Node two = this.getInternal(indexTwo);
		int save = one.getValue();
		one.setValue(two.getValue());
		two.setValue(save);
	}
	
	public String toString() {
		String string = "[";
	    for (Node noAtual = this.head; noAtual != this.tail; noAtual = noAtual.getNext()) {
	        string += noAtual.getValue() + ", ";
	    }
	    string += this.tail.getValue() + "]";
	    return string;
	}
	
	private class Node {
		
		private int value;
		private Node next;
		
		public Node(int element) {
			this.value = element;
			this.next = null;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
	}
}
