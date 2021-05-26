import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class BacktrackingBTreeForTests<T extends Comparable<T>> extends BacktrackingBTree<T>{
    HashMap<String, Node<T>> ids = new HashMap<>();
    HashMap<Node<T>,String> nodes = new HashMap<>();

    public BacktrackingBTreeForTests() {
        super();
    }
    public BacktrackingBTreeForTests(int order) {
        super(order);
    }


    private Node<T> getRootNode() {
        return root;
    }

    private void updateIDs(Node<T> n) {
        if(!nodes.containsKey(n)) {
            String id = UUID.randomUUID().toString();
            ids.put(id,n);
            nodes.put(n,id);
        }
        for(Node<T> child : n.children) {
            if(child != null)
                updateIDs(child);
        }
    }

    @Override
    public void insert(T value) {
        super.insert(value);
        Node<T> root = getRootNode();
        updateIDs(root);
    }

    private Node<T> getNode(String id) {
        return ids.get(id);
    }

    private String getId(Node<T> n) {
        return nodes.get(n);
    }

    public String getRootID() {
        if(root == null)
            return null;
        return getId(root);
    }

    public int numberOfChildrenOf(String id) {
        return getNode(id).getNumberOfChildren();
    }

    public String[] getChildrenOf(String id) {
        Node<T> n = getNode(id);
        String[] childrenIds = new String[n.numOfChildren];
        for(int i=0; i<childrenIds.length; i++)
            childrenIds[i] = getId(n.children[i]);
        return childrenIds;
    }

    public String getParentOf(String id) {
        Node<T> parent = getNode(id).parent;
        if(parent == null)
            return null;
        return getId(parent);
    }

    public T[] getKeys(String id) {
        return getNode(id).keys;
    }

    public int getNumberOfKeys(String id) {
        return getNode(id).getNumberOfKeys();
    }

    public String getStringOf(String id) {
        Node<T> n = getNode(id);
        if(n==null)
            return null;
        return n.toString();
    }
}
