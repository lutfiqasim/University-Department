package application;

public class HashTable<T> {
	private HNode<T>[] table = new HNode[11];

	public HashTable() {
		for (int i = 0; i < table.length; i++) {
			table[i] = new HNode<>(null);
		}
	}

	public void insert(T data) {
		if ((size() + 1) > (table.length / 2)) {
//			System.out.println("Rehashing");
			rehash();
//			System.out.println("new size = " + table.length);
		}
		int hashCode = data.hashCode();
		int index = (hashCode % (table.length));
		int quad = 1;
		HNode<T> curr = table[index];
		int tempIndex = index;
		while (curr.getData() != null) {
			if (curr.getFlag() == 'F') {
				index = tempIndex + (int) Math.pow(quad, 2);
				quad++;
				if (index >= table.length) {
					index = (index % (table.length));
				}
				curr = table[index];
			} else {
				break;
			}

		}
		table[index].setFlag('F');
		table[index].setData(data);
	}

	public HNode<T> delete(String key) {
//			System.out.println("previous size " + table.length);
//			System.out.println("new Size 2" + table.length);
		if (size() == 0)
			return null;
		else {
			int hashCode = StringHashCode(key);
			int index = (hashCode % (table.length));
			int tempIndex = index;
			int quad = 1;
			HNode<T> curr = table[index];
			while (curr.getData() != null) {
				if (curr.getFlag() == 'F' && curr.getData().equals(key)) {
					curr.setFlag('D');
					return curr;
				} else {
					index = tempIndex + (int) Math.pow(quad, 2);
					quad++;
					if (index >= table.length) {
						index = (index % (table.length));
					}
					curr = table[index];
				}
			}
			if ((size()) <= (table.length / 4)) {
//				System.out.println("shrinking");
				shrink();
			}
		}
		return null;
	}

	public HNode<T> find(String key) {
		if (size() == 0) {
			return null;
		} else {
			int hashCode = StringHashCode(key);
			int index = (hashCode % (table.length));
			int tempIndex = index;
			int quad = 1;
			HNode<T> curr = table[index];
			while (curr.getData() != null) {
				if (curr.getFlag() == 'F' && curr.getData().equals(key)) {
					return curr;
				}
				index = tempIndex + (int) Math.pow(quad, 2);
				quad++;
				if (index >= table.length)
					index = (index % (table.length));
				curr = table[index];
			}
		}
		return null;
	}

	public int size() {
		int i = 0;
		int size = 0;
		while (i < table.length) {
			if (table[i].getFlag() == 'F') {
				size++;
			}
			i++;
		}
		return size;
	}

	public int capacity() {
		return table.length;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private void rehash() {
		int newCapacity = nextPrime(table.length * 2);
		HNode<T>[] temp = table;
		table = new HNode[newCapacity];
		for (int i = 0; i < table.length; i++)
			table[i] = new HNode<>(null);
		for (int i = 0; i < temp.length; i++) {
			HNode<T> curr = temp[i];
			if (curr.getFlag() == 'F')
				insert(curr.getData());
		}
	}

	private void shrink() {
		int newCapacity = prevPrime((int) (table.length * .25));
		HNode<T>[] temp = table;
		table = new HNode[newCapacity];
		for (int i = 0; i < table.length; i++)
			table[i] = new HNode<>(null);
		for (int i = 0; i < temp.length; i++) {
			HNode<T> curr = temp[i];
			if (curr.getFlag() == 'F')
				insert(curr.getData());
		}
	}

	private boolean isPrime(int n) {
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 5; i < n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	private int nextPrime(int n) {
		int prime = n;
		boolean found = false;
		while (!found) {
			prime++;
			if (isPrime(prime))
				found = true;
		}
		return prime;
	}

	private int prevPrime(int n) {
		int prime = n;
		boolean found = false;
		while (!found && prime > 10) {
			prime--;
			if (isPrime(prime))
				found = true;
		}
		return prime;
	}

	private int StringHashCode(String name) {
		char ch[];
		ch = name.toCharArray();
		int xlength = name.length();
		int i, sum = 0;
		for (sum = 0, i = 0; i < xlength; i++) {
			sum += ch[i];
		}
		return sum;
//		int hash = 7;
//		for (int i = 0; i < name.length(); i++) {
//			hash = hash * 31 + name.charAt(i);
//		}
//		return hash;
	}

	public String getFullSpots() {
		String s = "";
		for (int i = 0; i < table.length; i++) {
			if (table[i].getFlag() == 'F')
				s += table[i] + "\n";
		}
		return s;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < table.length; i++) {
			if (table[i].getFlag() != 'D')
				s += table[i].getFlag() + "-->" + table[i] + "\n";
			else
				s += table[i].getFlag() + "-->null\n";
		}
		return s + "\n";
	}
}
