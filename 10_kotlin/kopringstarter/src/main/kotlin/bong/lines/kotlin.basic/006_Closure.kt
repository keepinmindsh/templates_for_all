package bong.lines.kotlin.basic

fun main(args: Array<String>) {
    val func = addFunc(10)
    val result = func(20)
    println(result)

    fun outer(): () -> Int{
        var x = 10
        var innerFunc = fun():Int{
            println(++x)
            return x
        }
        return innerFunc
    }

    val func1 = outer()
    func1() // 결과: 11
    func1() // 결과: 12
    func1() // 결과: 13
    func1() // 결과: 14
    func1() // 결과: 15
    func1() // 결과: 16
}

fun addFunc(x: Int): (Int) -> Int {
    return fun(y: Int): Int {
        return x + y
    }
}
