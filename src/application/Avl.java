package application;

public class Avl<T extends Comparable<T>> {
	private TNode<T> root;

	public boolean insert(T data) {
		boolean s = true;
		if (root == null)
			root = new TNode<T>(data);
		else {
			TNode<T> rootNode = root;
			s = addEntry(rootNode, data);
			root = rebalance(rootNode);
		}
		return s;
	}

	private boolean addEntry(TNode<T> rootNode, T data) {
		assert rootNode != null;// assert: ensures the correctness of an assumption here than node !=null
		// assumed to be true if not then it will throw an assertion error
		if (data.compareTo(rootNode.getData()) < 0) {// inserting into left or into left subtree
			if (rootNode.hasLeft()) {
				addEntry(rootNode.getLeft(), data);
				rootNode.setLeft(rebalance(rootNode.getLeft()));
			} else
				rootNode.setLeft(new TNode<T>(data));
		} else if (data.compareTo(rootNode.getData()) > 0) { // inserting into right or into right subtree
			if (rootNode.hasRight()) {
				addEntry(rootNode.getRight(), data);
				rootNode.setRight(rebalance(rootNode.getRight()));
			} else
				rootNode.setRight(new TNode<T>(data));
		} else {
			return false;
		}
		return true;

	}

	public TNode<T> delete(T data) {
		TNode<T> temp = deleteNode(data);
		if (temp != null) {
			TNode<T> rootNode = root;
			root = rebalance(rootNode);
		}
		return temp;
	}

	// deletion
	private TNode<T> deleteNode(T data) {
		TNode<T> current = root;
		TNode<T> parent = root;
		boolean isLeftChild = false;
		if (isEmpty())
			return null;
		while (current != null && !current.getData().equals(data)) {
			parent = current;
			if (data.compareTo(current.getData()) < 0) {// data is less than current node so go left
				current = current.getLeft();
				isLeftChild = true;
			} else {
				current = current.getRight();
				isLeftChild = false;
			}
		}
		if (current == null)// node to be deleted not found
			return null;
		if (current.isLeaf()) {// case 1:// if it has no children //Node to be deleted is a leaf.
			if (current.equals(root)) {// if data is root and it has no children delete root
				root = null;
			} else {
				if (isLeftChild) {
					parent.setLeft(null);
				} else
					parent.setRight(null);
			}
		}
		// case2: broken into to cases
		else if (current.hasLeft() && !current.hasRight()) {// if current has left child only
			if (current.equals(root)) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		} else if (current.hasRight() && !current.hasLeft()) {// if current has right child only
			if (current.equals(root)) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setLeft(current.getRight());
			} else
				parent.setRight(current.getRight());
		}
		// case 3: use successors
		else {
			TNode<T> successor = getSuccessor(current);
			if (current.equals(root))
				root = successor;
			else if (isLeftChild)
				parent.setLeft(successor);
			else
				parent.setRight(successor);
			successor.setLeft(current.getLeft());
		}
		return current;
	}

	private TNode<T> getSuccessor(TNode<T> node) {// gets the smallest item in the right subtree
		TNode<T> parentOfsuccessor = node;
		TNode<T> successor = node;
		TNode<T> current = node.getRight();
		while (current != null) {
			parentOfsuccessor = successor;
			successor = current;
			current = current.getLeft();
		}
		if (successor != node.getRight()) {// fix successor connection
			parentOfsuccessor.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}

	public boolean isEmpty() {
		return root == null;
	}

	private int getHeightDirfference(TNode<T> node) {
		if (!node.equals(null)) {
			int left = getHeight(node.getLeft());
			int right = getHeight(node.getRight());
			return left - right;
		}
		return 0;
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(TNode<T> node) {
		if (node != null) {
			int left = getHeight(node.getLeft());
			int right = getHeight(node.getRight());
			return 1 + Math.max(left, right);
		}
		return 0;
	}

	// Rotations
	// due to left-left addition
	private TNode<T> rotateRight(TNode<T> node) {// corrects an imbalance at a given node due to an
		// addition in the left subtree of node's leftChild
		TNode<T> nodeC = node.getLeft();// left child nodeN
		node.setLeft(nodeC.getRight());// sets node's left child to nodeC's right child
		nodeC.setRight(node);// sets nodeC's right child to node
		return nodeC;
	}

	// due to right right addition
	private TNode<T> rotateLeft(TNode<T> node) {// Corrects an imbalance at a given node due to
		// an addition in the right subtree of node's right child
		TNode<T> nodeC = node.getRight();// nodeC = right child of node
		node.setRight(nodeC.getLeft());// sets node's right child to nodeC's leftChild
		nodeC.setLeft(node);// set nodeC's left Child to nodeN
		return nodeC;
	}

	// due to right left addition
	private TNode<T> rotateRightLeft(TNode<T> node) {// Corrects an imbalance at a given node due to an addition
		// in the left subtree of node's right child
		TNode<T> nodeC = node.getRight();// nodeC = right child of node
		node.setRight(rotateRight(nodeC));// set node's right child to the node returned by rotateRight(nodeC(node's
											// right child rotated to right))
		return rotateLeft(node);// return node rotated left
	}

	// due to left right addition
	private TNode<T> rotateLeftRight(TNode<T> node) {// Corrects an imbalance at a given node due to addition
		// in the right subtree of node's left child
		TNode<T> nodeC = node.getLeft();
		node.setLeft(rotateLeft(nodeC));// sets node left child to returned by rotate left(nodeC)
		return rotateRight(node);// return the node rotated right
	}

	// Re-balancing
	private TNode<T> rebalance(TNode<T> node) {
		int diff = getHeightDirfference(node);
		if (diff > 1) {// addition was in node's left subtree (node's left subtree is taller than its
						// right subtree by more than 1)
			// addition was in node's left subtree
			if (getHeightDirfference(node.getLeft()) > 0)// left child of node has a left subtree that is
				// taller it's right subtree
				node = rotateRight(node);// addition was in left subtree of left child
			else
				node = rotateLeftRight(node);// addition was in right subtree of left child
		} else if (diff < -1) {// node's right subtree is taller than its left subtree by more than 1
			// addition was in node's right subtree
			if (getHeightDirfference(node.getRight()) < 0)// the right child of node has a right subtree that is
				// taler than its' left subtree
				node = rotateLeft(node);// addition was in right subtree of right child
			else
				node = rotateRightLeft(node);// addition was in left subtree of right child
		}
		return node;
	}

	public LinkedList<T> treeTolinkedList() {
		return treeToLinkedList(new LinkedList<T>(), root);
	}

	private LinkedList<T> treeToLinkedList(LinkedList<T> l1, TNode<T> node) {
		if (node != null) {
			if (node.hasLeft())
				treeToLinkedList(l1, node.getLeft());
			l1.insertAtHead(node.getData());
			if (node.hasRight())
				treeToLinkedList(l1, node.getRight());
			return l1;
		}
		return null;
	}

	// traverse
	public String inOrderTrarverse() {
		StringBuilder string = new StringBuilder();
		inOrderTrarverse(root, string);
		return string.toString();
	}

	private void inOrderTrarverse(TNode<T> node, StringBuilder string) {
		if (node != null) {
			if (node.hasLeft())
				inOrderTrarverse(node.getLeft(), string);
			string.append(node + "\n");
			if (node.hasRight())
				inOrderTrarverse(node.getRight(), string);
		}
	}

	// find
	public TNode<T> find(T data) {
		return find(data, root);
	}

	private TNode<T> find(T data, TNode<T> node) {
		if (node != null) {
			if (node.getData().compareTo(data) == 0)
				return node;
			else if (node.getData().compareTo(data) > 0 && node.hasLeft())
				return find(data, node.getLeft());
			else {
				if (node.hasRight())
					return find(data, node.getRight());
			}
		}
		return null;
	}

	public int size() {
		return size(root);
	}

	private int size(TNode<T> node) {
		if (node == null)
			return 0;
		else
			return (size(node.getLeft()) + 1 + size(node.getRight()));
	}

	public boolean isABST() {
		return isABST(root);
	}

	private boolean isABST(TNode<T> node) {
		if (node == null)
			return true;
		/* false if the max of the left is > than us */
		if (node.hasLeft() && largestValue(node.getLeft()).getData().compareTo(node.getData()) > 0) {
			return false;
		}
		/* false if the min of the right is <= than us */
		if (node.hasRight() && smallestValue(node.getRight()).getData().compareTo(node.getData()) <= 0) {
			return false;
		}
		if (!isABST(node.getLeft()) && !isABST(node.getRight()))
			return false;

		return true;
	}

	public TNode<T> largestValue() {
		return largestValue(root);
	}

	private TNode<T> largestValue(TNode<T> node) {// Go to the right most value
		if (node != null) {
			if (!node.hasRight())// if this the most right return it
				return node;
			// else call it with the right of current node
			return largestValue(node.getRight());
		}
		// if not found
		return null;
	}

	public TNode<T> smallestValue() {
		return smallestValue(root);
	}

	private TNode<T> smallestValue(TNode<T> node) {
		if (node != null) {
			if (!node.hasLeft())
				return node;
			return smallestValue(node.getLeft());
		}
		return null;
	}

	@Override
	public String toString() {
		String s = "";
		s += inOrderTrarverse();
		return s;
	}

}
