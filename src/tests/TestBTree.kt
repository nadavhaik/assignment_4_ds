package tests
import BacktrackingBTreeForTests
import java.lang.AssertionError
import java.util.*
import kotlin.random.Random.Default.nextInt
import kotlin.test.*

fun validateNode(t: BacktrackingBTreeForTests<Int>, n: String?, order: Int, isRoot: Boolean=false) {
    if(n == null)
        return
    val numOfKeys = t.getNumberOfKeys(n)
    if(isRoot)
        assertGreaterOrEqual(numOfKeys, 1)
    else
        assertGreaterOrEqual(numOfKeys, order-1)
    assertSmaller(numOfKeys, 2*order)
    val children = t.getChildrenOf(n)
    children.forEach {child ->
        if(child != null) {
            val parent = t.getParentOf(child)
            assertEquals(n, parent,"\nnode: ${t.getStringOf(child)}\n" +
                    "expected parent: ${t.getStringOf(n)}\n" +
                    "got: ${t.getStringOf(parent)}\n" +
                    "in tree:\n" +
                    t.toString())
            validateNode(t, child, order)
        }
    }
}
fun validateTree(t: BacktrackingBTreeForTests<Int>, order: Int) {
    val root = t.rootID
    if(root != null) {
        validateNode(t, root, order, true)
    }
}

fun recreate(randomValues: List<Int>, failIndex: Int){
    var t = BacktrackingBTreeForTests<Int>()
    var dequeue = LinkedList<String>()
    randomValues.forEach {value ->
        t.insert(value)
        validateTree(t, 2)
        dequeue.addLast(t.toString())
    }
    var i = 100
    do {
        var stateBeforeInsertion = dequeue.pollLast()
        assertEquals(stateBeforeInsertion, t.toString(), "failed for i=$i")
        validateTree(t, 2)
        i--
        t.Backtrack()
    } while (t.size() > 0)
}

fun main(args: Array<String>) {
    // test 1:
    var t = BacktrackingBTreeForTests<Int>()
    for(i in 0..10) {
        if(i != 5)
            t.insert(i)
    }
    var stringBefore = t.toString()
    t.insert(5)
    assertNotEquals(stringBefore, t.toString())
    t.Backtrack()
    validateTree(t, 2)
    assertEquals(stringBefore, t.toString())

    // test 2:
    for(order in 2..100) {
        t = BacktrackingBTreeForTests<Int>(order)
        var dequeue = LinkedList<String>()
        var randomValues = List(500) { nextInt(-100000, 100000) }
        randomValues.forEach {value ->
            t.insert(value)
            validateTree(t, order)
            dequeue.addLast(t.toString())
        }
        var i = 0
        do {
            var stateBeforeInsertion = dequeue.pollLast()
            assertEquals(stateBeforeInsertion, t.toString(), "$i")
            validateTree(t, order)
            t.Backtrack()
            i++
        } while (t.size() > 0)
    }

}
