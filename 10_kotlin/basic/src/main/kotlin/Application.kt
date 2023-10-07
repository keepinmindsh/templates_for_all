import sample.*

fun main() {
    println("Hello World!")

    val classSamples = ClassSamples()
    "$classSamples".also(::println)

    val constructorSample = ConstructorSample(InstanceName = "Dream Come True")
    "$constructorSample.instanceName".also(::println)

    val constructorSample2 = ConstructorSample2(InstanceName = "Dream Come True")
    "$constructorSample2".also(::println)

    val constructorSample3 = ConstructorSample3(firstName = "Dream", lastName = "Come", age = 55)
    "$constructorSample3.firstName,$constructorSample3.lastName,$constructorSample3.age".also(::println)

    val person = Person()
    person.pets



}