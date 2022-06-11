package bong.lines.kotlin.basic

data class Person(val name : String, var age : Int? = null)

fun main() {
    val persons = listOf(Person("영희"), Person("철수", age = 20))

    val oldest = persons.maxByOrNull { it.age ?: 0 }
    println("나이가 가장 많은 사람 : $oldest ")
}