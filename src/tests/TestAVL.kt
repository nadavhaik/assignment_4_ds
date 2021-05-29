package tests
import BacktrackingAVLForTests
import java.util.*
import kotlin.AssertionError
import kotlin.math.abs
import kotlin.random.Random
import kotlin.test.*

const val LIST_SIZE = 100
const val ITERATIONS = 10

fun calculateHeight(t:BacktrackingAVLForTests, n: Int?): Int {
    if(n==null)
        return 0
    var leftHeight = 1+calculateHeight(t, t.getLeftChildOf(n))
    var rightHeight = 1+calculateHeight(t, t.getRightChildOf(n))
    return Math.max(leftHeight, rightHeight)
}

fun validateNode(t:BacktrackingAVLForTests, n: Int) {
    var expectedHeight = calculateHeight(t, n)
    assertEquals(t.getHeightOf(n), expectedHeight, "Invalid height for node $n")
    val left = t.getLeftChildOf(n)
    val right = t.getRightChildOf(n)
    val balanceFactor = t.getBalanceFactorOf(n)
    if(abs(balanceFactor) > 1)
        throw AssertionError("node $n with left=$left and right=$right has an unbalanced factor: $balanceFactor" +
        " in tree:\n$t")

    if(left != null) {
        assertEquals(n, t.getParentOf(left))
        validateNode(t, left)
    }
    if(right != null) {
        assertEquals(n, t.getParentOf(right))
        validateNode(t, right)
    }
}

fun validateTree(t: BacktrackingAVLForTests) {
    if(t.root != null)
        validateNode(t, t.root)
}

fun main(args: Array<String>) {
    var t = BacktrackingAVLForTests()
    assertNull(t.root)
    assertEquals(t.toString(), "")
    t.Backtrack() // shouldn't do anything
    assertEquals(t.toString(), "")
    for(j in 0..ITERATIONS) {
        t = BacktrackingAVLForTests()
        var dequeue = LinkedList<String>()
        var randomValues = List(LIST_SIZE) { Random.nextInt(-100000, 100000) }
        var uniqueList = ArrayList<Int>()
        randomValues.forEach{value->
            if(!uniqueList.contains(value))
                uniqueList.add(value)
        }
        uniqueList.forEach {value->
            t.insert(value)
            validateTree(t)
            dequeue.addLast(t.toString())
        }
        var i = LIST_SIZE
        do {
            validateTree(t)
            assertEquals(dequeue.pollLast(), t.toString(), "failed for i=$i")
            i--
            t.Backtrack()
        } while (!t.isEmpty)
    }

}



