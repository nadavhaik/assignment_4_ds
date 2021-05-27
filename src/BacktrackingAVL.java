import java.util.ArrayList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature(); // Reminder!
        if (backtrackDeque.isEmpty())
            return;
        ImbalanceCases ourCase = (ImbalanceCases)backtrackDeque.pollLast();
        Node<Integer> FirstOutOfBalance = (node<Integer>)backtrackDeque.pollLast();
        Node<Integer> NodeToDelete = (node<Integer>)backtrackDeque.pollLast();
        Node<Integer> rotationNode = NodeToDelete.parent;
        if(FirstOutOfBalance == null) {
            deleteNode(NodeToDelete)
        }
        else {
                if (ourCase == LEFT_LEFT) {
                    rotationNode = leftRotate(rotationNode);
                }
                else if (ourCase == LEFT_RIGHT) {
                    rotationNode = leftRotate(rotationNode);
                    rotationNode = rightRotate(rotationNode);
                }
                else if (ourCase == RIGHT_LEFT) {
                    rotationNode = rightRotate(rotationNode);
                    rotationNode = leftRotate(rotationNode);
                }
                else if (ourCase == RIGHT_RIGHT) {
                    rotationNode = rightRotate(rotationNode);
                }
            deleteNode(NodeToDelete)
            }
        }
    }

    private void deleteNode(Node node) {
        Node<Integer> currParent = node.parent;
        if (currParent.left == node) {
            keyToDelete.parent = null;
            currParent.left = null;
        } else {
            keyToDelete.parent = null;
            currParent.right = null;
        }
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
        IntegrityStatement.signature();
        List<Integer> values = new ArrayList<>();
        values.add(4);
        values.add(2);
        values.add(8);
        values.add(1);
        values.add(3);
        values.add(6);
        values.add(10);
        values.add(5);
        values.add(7);
        values.add(9);
        values.add(11);

        values.add(12);
        return values;
    }
}

