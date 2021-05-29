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
        Node FirstOutOfBalance = (Node) backtrackDeque.pollLast ();
        Node NodeToDelete = (Node) backtrackDeque.pollLast ();
        Node NodeParent = NodeToDelete.parent;
//        System.out.println(NodeToDelete.value);
        if (FirstOutOfBalance == null || ourCase == null ) {
            deleteNode (NodeToDelete);
        } else {
            Node rotationNode1 = FirstOutOfBalance.parent;

            if (ourCase == ImbalanceCases.LEFT_LEFT) {
                rotationNode1 = leftRotate (rotationNode1);
                newPointer(rotationNode1);

            } else if (ourCase == ImbalanceCases.LEFT_RIGHT) {
                rotationNode1 = leftRotate (rotationNode1);
                newPointer(rotationNode1);
                Node rotationNode2 = rotationNode1.left;
                rotationNode2 = rightRotate (rotationNode2);
                newPointer(rotationNode2);

            } else if (ourCase == ImbalanceCases.RIGHT_LEFT) {
                rotationNode1 = rightRotate (rotationNode1);
                newPointer(rotationNode1);
                Node rotationNode2 = rotationNode1.right;
                rotationNode2 = leftRotate (rotationNode2);
                newPointer(rotationNode2);

            } else if (ourCase == ImbalanceCases.RIGHT_RIGHT) {
                rotationNode1 = rightRotate (rotationNode1);
                newPointer(rotationNode1);
            }
            NodeParent = NodeToDelete.parent;
            deleteNode (NodeToDelete);
        }
        if(NodeParent != null) {
            while (NodeParent != null) {
                newHeight(NodeParent);
                NodeParent = NodeParent.parent;
            }
        }
        else {
            root = null;
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


    private void deleteNode(Node NodeToDelete) {
        if(NodeToDelete == root)
            root = null;
        else {
            Node currParent = NodeToDelete.parent;
            if (currParent.left == NodeToDelete) {
                NodeToDelete.parent = null;
                currParent.left = null;
            } else {
                NodeToDelete.parent = null;
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



    public static void main(String[] args) {
        BacktrackingAVL T = new BacktrackingAVL();
        T.insert (40);
        T.insert (20);
        T.insert (50);
        T.insert (10);
        T.printTree ();
        T.insert (30);
        T.insert (60);
        T.insert (35);
//        T.printTree ();

        System.out.println();
        System.out.println();

        T.insert (32);
//        T.printTree ();


//        ImbalanceCases ourCase = (ImbalanceCases) T.getValueS();
//        Node FirstOutOfBalance = (Node) T.getValueS();
//        Node NodeToDelete = (Node) T.getValueS();
//        System.out.println(ourCase);
//        System.out.println(FirstOutOfBalance.value);
//        System.out.println(NodeToDelete.value);



        T.Backtrack();
        T.Backtrack();
        T.Backtrack();
        T.Backtrack();
        T.printTree ();


    }
}

