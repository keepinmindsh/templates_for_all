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

fun printHello(name: String?): Unit {
    if(name != null){
        println("Hello $name")
    }else{
        println("Hi There!")
    }
}

fun ourDream(x : Int) : Int = x * 50

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for(t in ts)
        result.add(t)
    return result
}

infix fun Int.plus(x: Int): Int {
    return this + x
}

infix fun String.substringMatches(r: Regex) : List<String> {
    return r.findAll(this)
        .map { it.value }
        .toList()
}

fun add(x: Int): (Int) -> Int {
    return fun(y: Int): Int {
        return x + y
    }
}

fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun higherOrderFunction(){
    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas are code blocks enclosed in curly braces.
    items.fold(0) {
        // When a lambda has parameters, they go first, followed by '->'
            acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // The last expression in a lambda is considered the return value:
        result
    }

    // Parameter types in a lambda are optional if they can be inferred:
    val joinedToString = items.fold("Elements:") { acc, i -> "$acc $i" }
    println(joinedToString)

    // Function references can also be used for higher-order function calls:
    val product = items.fold(1, Int::times)
    println(product)

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

    printHello("Value")

    println(ourDream(50))

    println(asList(1,2,3,4,5,6))

    println(10 plus 5)

    val matches = "a bc def" substringMatches ".*? ".toRegex()
    println(matches)

    val func = add(10)
    func(20)
    val closureValue = func(20)
    println(closureValue)

    higherOrderFunction()
}


