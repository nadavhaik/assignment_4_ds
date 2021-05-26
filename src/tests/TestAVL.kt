package tests
import BacktrackingAVLForTests
import java.util.*
import kotlin.AssertionError
import kotlin.math.abs
import kotlin.random.Random
import kotlin.test.*

const val LIST_SIZE = 1000
const val ITERATIONS = 1000

fun validateNode(t:BacktrackingAVLForTests, n: Int, expectedHeight: Int) {
    assertEquals(t.getHeightOf(n), expectedHeight, "Invalid height for node $n")
    val left = t.getLeftChildOf(n)
    val right = t.getRightChildOf(n)
    val balanceFactor = t.getBalanceFactorOf(n)
    if(abs(balanceFactor) >= 1)
        throw AssertionError("node $n with left=$left and right=$right has an unbalanced factor: $balanceFactor")

    if(left != null) {
        assertEquals(n, t.getParentOf(left))
        validateNode(t, left, expectedHeight+1)
    }
    if(right != null) {
        assertEquals(n, t.getParentOf(right))
        validateNode(t, right, expectedHeight+1)
    }
}

fun validateTree(t: BacktrackingAVLForTests) {
    if(t.root != null)
        validateNode(t, t.root, 0)
}

fun main(args: Array<String>) {
    assertEquals(1,2, "1234")
    var t = BacktrackingAVLForTests()
    assertNull(t.root)
    assertEquals(t.toString(), "")
    t.Backtrack() // shouldn't do anything
    assertEquals(t.toString(), "")
    for(j in 0..ITERATIONS) {
        t = BacktrackingAVLForTests()
        var dequeue = LinkedList<String>()
        var randomValues = List(LIST_SIZE) { Random.nextInt(-100000, 100000) }
        randomValues.forEach {
            t.insert(it)
            dequeue.addLast(t.toString())
        }
        var i = LIST_SIZE
        do {
            assertEquals(dequeue.pollLast(), t.toString(), "failed for i=$i")
            i--
            t.Backtrack()
        } while (!t.isEmpty)
    }

}



