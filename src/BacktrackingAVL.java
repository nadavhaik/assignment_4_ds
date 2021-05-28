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
        if (FirstOutOfBalance == null) {
            deleteNode (NodeToDelete);
        } else {
            Node rotationNode1 = FirstOutOfBalance.parent;
//            System.out.println(rotationNode1.value);
            if (ourCase == ImbalanceCases.LEFT_LEFT) {
                rotationNode1 = leftRotate (rotationNode1);
//                System.out.println(rotationNode1.value);
//                System.out.println(rotationNode1.left.value);
//                System.out.println(rotationNode1.left.left.value);
            } else if (ourCase == ImbalanceCases.LEFT_RIGHT) {
                rotationNode1 = leftRotate (rotationNode1);
                Node rotationNode2 = rotationNode1.left;
                rotationNode2 = rightRotate (rotationNode2);
            } else if (ourCase == ImbalanceCases.RIGHT_LEFT) {
                rotationNode1 = rightRotate (rotationNode1);
                Node rotationNode2 = rotationNode1.right;
                rotationNode2 = leftRotate (rotationNode2);
            } else if (ourCase == ImbalanceCases.RIGHT_RIGHT) {
                rotationNode1 = rightRotate (rotationNode1);
            }
//            System.out.println(NodeToDelete.parent.value);
            deleteNode (NodeToDelete);
        }
    }


    private void deleteNode(Node NodeToDelete) {
        Node currParent = NodeToDelete.parent;
        if (currParent.left == NodeToDelete) {
            NodeToDelete.parent = null;
            currParent.left = null;
        } else {
            NodeToDelete.parent = null;
            currParent.right = null;
        }
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
        T.insert (30);
        T.insert (60);
        T.insert (7);
        T.printTree ();

        T.insert (6);
        T.printTree ();


//        ImbalanceCases ourCase = (ImbalanceCases) T.getValueS();
//        Node FirstOutOfBalance = (Node) T.getValueS();
//        Node NodeToDelete = (Node) T.getValueS();
//        System.out.println(ourCase);
//        System.out.println(FirstOutOfBalance.value);
//        System.out.println(NodeToDelete.value);



        T.Backtrack();
        T.printTree ();


    }
}

