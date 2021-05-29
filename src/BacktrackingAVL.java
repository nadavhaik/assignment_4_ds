import java.util.ArrayList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

    public BacktrackingAVL() {
        super();
    }
    //You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature (); // Reminder!
        if (backtrackDeque.isEmpty ())
            return;
        ImbalanceCases ourCase = (ImbalanceCases) backtrackDeque.pollLast ();
        Node firstOutOfBalance = (Node) backtrackDeque.pollLast ();
        Node nodeToDelete = (Node) backtrackDeque.pollLast ();
        Node nodeParent = nodeToDelete.parent;
        if(ourCase != null) {
            Node rotationNode1 = firstOutOfBalance.parent;
            Node rotationNode2;
            switch(ourCase) {
                case LEFT_LEFT:
                    rotationNode1 = leftRotate(rotationNode1);
                    newPointer(rotationNode1);
                    break;
                case LEFT_RIGHT:
                    rotationNode1 = leftRotate(rotationNode1);
                    newPointer(rotationNode1);
                    rotationNode2 = rotationNode1.left;
                    rotationNode2 = rightRotate(rotationNode2);
                    newPointer(rotationNode2);
                    break;
                case RIGHT_LEFT:
                    rotationNode1 = rightRotate(rotationNode1);
                    newPointer(rotationNode1);
                    rotationNode2 = rotationNode1.right;
                    rotationNode2 = leftRotate(rotationNode2);
                    newPointer(rotationNode2);
                    break;
                case RIGHT_RIGHT:
                    rotationNode1 = rightRotate(rotationNode1);
                    newPointer(rotationNode1);
                    break;
            }
            nodeParent = nodeToDelete.parent;
        }

        deleteNode(nodeToDelete);
        if(nodeParent == null)
            root = null;
        while (nodeParent != null) {
            newHeight(nodeParent);
            nodeParent = nodeParent.parent;
        }
    }


    private void newPointer (Node rotationNode){
        if (rotationNode.parent != null){
            if (rotationNode.parent.value > rotationNode.value)
                rotationNode.parent.left = rotationNode;
            else
                rotationNode.parent.right = rotationNode;
        }
        else
            root = rotationNode;
    }


    private void deleteNode(Node nodeToDelete) {
        if(nodeToDelete == root)
            root = null;
        else {
            Node currParent = nodeToDelete.parent;
            if (currParent.left == nodeToDelete) {
                nodeToDelete.parent = null;
                currParent.left = null;
            } else {
                nodeToDelete.parent = null;
                currParent.right = null;
            }
        }
    }
    private void newHeight(Node node) {
        node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;
    }

    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        IntegrityStatement.signature ();
        List<Integer> values = new ArrayList<> ();
        values.add (4);
        values.add (2);
        values.add (8);
        values.add (1);
        values.add (3);
        values.add (6);
        values.add (10);
        values.add (5);
        values.add (7);
        values.add (9);
        values.add (11);

        values.add (12);
        return values;
    }
}

