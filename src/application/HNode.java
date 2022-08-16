package application;

public class HNode<T> {
	private T data;
	private char flag = 'E';

	public HNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return data + "";
	}

}
