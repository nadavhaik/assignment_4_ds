package tests

fun assertSmaller(actual: Int, expected: Int, message: String? = "") {
    if(actual >= expected)
        throw AssertionError("$message. $actual ≮ $expected ")
}
fun assertSmallerOrEqual(actual: Int, expected: Int, message: String? = "") {
    if(actual > expected)
        throw AssertionError("$message. $actual ≰ $expected ")
}
fun assertGreater(actual: Int, expected: Int, message: String? = "") {
    if(actual <= expected)
        throw AssertionError("$message. $actual ≯ $expected ")

}
fun assertGreaterOrEqual(actual: Int, expected: Int, message: String? = "") {
    if(actual < expected)
        throw AssertionError("$message. $actual ≱ $expected ")

}