package application;

public class Node<T extends Comparable<T>> {
	// Stores data of current node
	private T data;
	// Storing address of next node
	private Node<T> next;

	public Node(T data) {

		setData(data);
		this.next = null;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getNext() {
		return next;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}