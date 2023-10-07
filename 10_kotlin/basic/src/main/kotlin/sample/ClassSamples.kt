package sample

class ClassSamples {
}

// constructor 를 이용해 생성자 생성 시점에 값을 정의할 경우
class ConstructorSample constructor(InstanceName:String) {
    val instanceName = InstanceName.uppercase()
}

// constructor 를 축약해서 아래와 같이 작성할 수 있다.
class ConstructorSample2(InstanceName: String) {
    init {
        "First initializer $InstanceName".also(::println)
    }
}

// constructor 에서 변수를 정의하여 생성자를 생성하는 방식
class ConstructorSample3(var firstName: String, var lastName: String, var age: Int){
    init {
        "$firstName, $lastName, $age".also(::println)
    }
}

class Person(val pets: MutableList<Pet> = mutableListOf())

class Pet {
    constructor(owner: Person) {
        owner.pets.add(this)
    }
}

class PersonForSecondaryConstructor(val name: String) {
    val children: MutableList<PersonForSecondaryConstructor> = mutableListOf()
    constructor(name: String, parent: PersonForSecondaryConstructor) : this(name) {
        parent.children.add(this)
    }
}

class Constructors {
    init {
        println("Init Block")
    }

    constructor(i: Int) {
        println("Constructor $i")
    }
}