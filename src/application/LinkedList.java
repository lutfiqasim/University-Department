package application;

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;

	public LinkedList() {
		this.head = null;
	}

	public LinkedList(T data) {
		Node<T> temp = new Node<T>(data);
		head = temp;
	}

	public Node<T> getHead() {
		return head;
	}

	public void insertAtHead(T data) {
		if (head == null)
			head = new Node<T>(data);
		else {
			Node<T> newNode = new Node<T>(data);
			newNode.setNext(head);
			head = newNode;
		}
	}

//	public void insertOrdered(T data) {
//		Node<T> temp1 = new Node<>(data);
//		Node<T> prev = null, curr = head;
//
//		for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
//			;
//		if (head == null) // empty list
//			head = temp1;
//		else if (prev == null) { // add at first
//			temp1.setNext(head);
//			head = temp1;
//		} else if (curr == null) { // add at last
//			prev.setNext(temp1);
//		} else { // insert between previous and current
//			temp1.setNext(curr);
//			prev.setNext(temp1);
//		}
//
//	}

	public void show() {
		Node<T> temp = head;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}

	}

	public void traverse() {
		traverse(head);
	}

	private void traverse(Node<T> temp) {
		if (head == temp)
			System.out.print("Head -->\n");
		if (temp == null) {
			System.out.println("Null");
		} else {
			System.out.print(temp.getData() + "-->");
			traverse(temp.getNext());
		}

	}

	public int lenght() {
		Node<T> temp = head;
		int size = 0;
		while (temp != null) {
			size++;
			temp = temp.getNext();
		}
		return size;
	}

//	public void delete(T data) {
//		if (head == null) {
//			System.out.println("Empty list");
//			return;
//		}
//		if (head.getData().equals(data))
//			head = head.getNext();
//		else {
//			Node<T> curr = head;
//			Node<T> prev = null;
//			for (; curr != null && curr.getData().compareTo(data) < 0; prev = curr, curr = curr.getNext())
//				;
//			if (curr == null) {
//				System.out.println("No such data in Linked List");
//			} else if (curr.getData().equals(data)) {
//				prev.setNext(curr.getNext());
//			}
//		}
//	}
	public void deleteFirst() {
		if (head == null) {
			return;
		}
		head = head.getNext();
	}

	public Node<T> find(T data) {
		Node<T> temp = head;
		while (temp != null) {
			if (temp.getData().equals(data)) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}

	public String toString() {
		Node<T> temp = head;
		String s = "";
		while (temp.getNext() != null) {
			s += temp.toString() + "->\n";
			temp = temp.getNext();
		}
		s += " Null";
		return s;
	}

}
