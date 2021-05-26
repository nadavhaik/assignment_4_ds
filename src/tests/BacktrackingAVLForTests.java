public class BacktrackingAVLForTests extends BacktrackingAVL {
    private Node search(int k) {
        // simple binary search
        Node result = null;
        if(!isEmpty()) {
            Node node = root;
            while (true) {
                // binary search via iterator
                int val =  node.value;
                if (k == val) {
                    // node was found
                    result = node;
                    break;
                }
                else if (k < val) {
                    if (node.left == null) // val isn't in the tree
                        break;
                    node = node.left;
                } else {
                    if (node.right == null) // val isn't in the tree
                        break;
                    node = node.right;
                }
            }
        }
        return result;
    }

    private Node searchAndAssertExistenceOf(int value) {
        Node n = search(value);
        if(n==null)
            throw new IllegalArgumentException("Given value " + value + " isn't in the tree!!!");
        return n;
    }

    public Integer getRoot() {
        if(root == null)
            return null;
        return root.value;
    }

    public Integer getLeftChildOf(int value) {
        Node n = searchAndAssertExistenceOf(value);
        if(n.left == null)
            return null;
        return n.left.value;
    }

    public Integer getRightChildOf(int value) {
        Node n = searchAndAssertExistenceOf(value);
        if(n.right == null)
            return null;
        return n.right.value;
    }

    public Integer getParentOf(int value) {
        Node n = searchAndAssertExistenceOf(value);
        if(n.parent == null)
            return null;
        return n.parent.value;
    }

    public Integer getHeightOf(int value) {
        return searchAndAssertExistenceOf(value).height;
    }

    public int getBalanceFactorOf(int value) {
        return getBalanceFactor(searchAndAssertExistenceOf(value));
    }

    public boolean isEmpty() {
        return root == null;
    }

    private static String getNodeString(Node n) {
        String s = "";
        if(n.right != null) {
            s += getNodeString(n.right);
            s += "\n";
        }
        for(int i=0; i<n.height; i++)
            s+="  ";
        s += n.value;
        if(n.left != null) {
            s += "\n";
            s += getNodeString(n.left);
        }
        return s;
    }

    public String toString() {
        if(this.root == null)
            return "";
        return getNodeString(this.root);
    }


}
