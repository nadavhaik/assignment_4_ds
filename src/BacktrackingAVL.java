import java.util.ArrayList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature(); // Reminder!
        if (backtrackDeque.isEmpty())
            return;
        ImbalanceCases ourCase = (ImbalanceCases)backtrackDeque.pollLast();
        node<Integer> FirstOutOfBalance = (node<Integer>)backtrackDeque.pollLast();
        node<Integer> keyToDelete = (node<Integer>)backtrackDeque.pollLast();
        if(FirstOutOfBalance == null) {
            node<Integer> currParent = keyToDelete.parent;
            if (currParent.left == keyToDelete) {
                keyToDelete.parent = null;
                currParent.left = null;
            }
            else{
                keyToDelete.parent = null;
                currParent.right = null;
            }



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
