import java.util.ArrayList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

	//You are to implement the function Backtrack.
	@SuppressWarnings("unchecked")
	public void Backtrack() {
		IntegrityStatement.signature();
		if(backtrackDeque.isEmpty())
			return;
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
		Node<T> split = (Node<T>)backtrackDeque.pollLast();
	    while (split != null) {
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
			} else {
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
			split = (Node<T>)backtrackDeque.pollLast();
		}
    }
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
	    List<Integer> values = new ArrayList<>();
	    // B-Tree with t = 2
	    values.add(5);
		values.add(6);
		values.add(7);

		values.add(8);
	    return values;
	}
}
