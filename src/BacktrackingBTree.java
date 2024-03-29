import java.util.ArrayList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	public BacktrackingBTree(int order) {
		super (order);
	}

	public BacktrackingBTree() {
		super ();
	}


	//You are to implement the function Backtrack.
	@SuppressWarnings("unchecked")
	public void Backtrack() {
		IntegrityStatement.signature();
		if(backtrackDeque.isEmpty()) // there's nothing to backtrack
			return;
		// first - backtracking the actual insertion
	    T keyToDelete = (T)backtrackDeque.pollLast();
	    Node<T> n = (Node<T>) backtrackDeque.pollLast();
	    n.removeKey(keyToDelete);
	    size--;
	    if(n.numOfKeys == 0) {
	    	if(n.parent != null)
	    		n.parent.removeChild(n);
			else
				root = null;
		}

	    // second - backtracking all the splits (if happened)
		Node<T> split = (Node<T>)backtrackDeque.pollLast();
	    while (split != null) {
	    	// backtracking the split:
	    	T mv = (T)backtrackDeque.pollLast();
	    	Node<T> parent = (Node<T>)backtrackDeque.pollLast();
	    	int mvIndex = parent.indexOf(mv);
			Node<T> leftChild = parent.getChild(mvIndex);
			Node<T> rightChild = parent.getChild(mvIndex+1);
			parent.removeKey(mvIndex);
			if(parent.numOfKeys > 0) {
				parent.removeChild(leftChild);
				parent.removeChild(rightChild);
				parent.addChild(split);
				split.parent = parent;
			} else { // an edge case that may happen if parent is the actual root
				if(parent.parent == null) {
					root = split;
					split.parent = null;
				} else {
					Node<T> grandParent = parent.parent;
					grandParent.removeChild(parent);
					grandParent.addChild(split);
					split.parent = grandParent;
				}
			}

			// retrieving the pointers for the children:
			for(Node<T> child : split.children) {
				if (child != null)
					child.parent = split;
			}
			split = (Node<T>)backtrackDeque.pollLast();
		}
    }

	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		IntegrityStatement.signature();
		List<Integer> values = new ArrayList<>();

		// this one is for B-Tree with t = 2
	    values.add(5);
		values.add(6);
		values.add(7);

		values.add(8);
		return values;
	}
}
