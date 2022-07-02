package bong.lines.kotlin.basic

// Functions
fun sum(a: Int, b: Int): Int {
    return a + b
}

// Functions - Expression
fun sumExpression(a: Int, b: Int) = a + b

// A function that returns no meaningful value.
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

// Unit return type can be omitted.
fun printSumOmitted(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun double(i: Double): Double {
    return i
}

fun read(
    b: ByteArray,
    off: Int = 0,
    len: Int = b.size
) : Int {
    return off * len
}

open class A {
    open fun foo(i: Int = 10): Int {
        return i
    }
}

class B : A() {
    override fun foo(i: Int): Int {
        return i
    }
}

fun reformat(
    str: String,
    normalizeCase: Boolean = true,
    upperCaseFirstLetter: Boolean = true,
    divideByCamelHumps: Boolean = false,
    wordSeparator: Char = ' ',
) {
    println(str)
    println(normalizeCase)
    println(upperCaseFirstLetter)
    println(divideByCamelHumps)
    println(wordSeparator)
}

fun main() {
    print("sum of 3 and 5 is ")
    println(sum(3, 5))

    println("sum of 19 and 23 is ${sumExpression(19, 23)}")

    printSum(10,20)

    printSumOmitted(30, 40)

    val result = double(2.0)
    println("$result")

    read(
        "value".toByteArray(),
        10
    )

    println(B().foo(300))

    reformat(
        "Value",
        upperCaseFirstLetter = false
    )
}


