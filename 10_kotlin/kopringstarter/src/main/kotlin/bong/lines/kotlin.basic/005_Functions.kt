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

fun main() {
    print("sum of 3 and 5 is ")
    println(sum(3, 5))

    println("sum of 19 and 23 is ${sumExpression(19, 23)}")

    printSum(10,20)

    printSumOmitted(30, 40)

    val result = double(2.0)
    print("$result")

    read(
        "value".toByteArray(),
        10
    )
}


