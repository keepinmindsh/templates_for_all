package bong.lines.kotlin.basic


fun main(){
    // 오직 단 한번만 할당 가능한 keyword - val
    val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment

    println("$a $b $c")

    // 여러변 변수 할당이 가능한 keyword - var
    var x = 5 // `Int` type is inferred
    x += 1

    println("$x")

    // Properties 설정 및 사용
    // -> https://kotlinlang.org/docs/properties.html#getters-and-setters
    val address = copyAddress(Address())

    println(address.street)
}

// Properties
class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street
    return result
}
