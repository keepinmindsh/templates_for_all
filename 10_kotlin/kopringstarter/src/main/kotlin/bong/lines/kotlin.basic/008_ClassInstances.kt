package bong.lines.kotlin.basic

open class Shape

class Rectangle(var height: Double, var length: Double) : Shape() {
    var perimeter = (height + length) * 2
}

class ClassSample constructor(firstName: String)

// When creating class with init by order
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

// Init And Constructor
class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor $i")
    }
}

// Nested And Inner Class
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

// Inner Class
class OuterWithInner {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

// Inheritance
open class Base(p: Int)

class Derived(p: Int) : Base(p){
    init {
        println("$p")
    }
}

// Overriding Methods
open class OverridingMethod {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
}

class OverrideClass() : OverridingMethod() {
    override fun draw() {
        /*...*/
        println("draw your dream!")
    }
}

fun main(){
    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")

    InitOrderDemo("Sample")

    Constructors(100)

    val demo = Outer.Nested().foo() // == 2
    println("$demo")

    val demoWithInner = OuterWithInner().Inner().foo() // == 1
    println("$demoWithInner")

    Derived(10000)

    OverrideClass().draw()
}