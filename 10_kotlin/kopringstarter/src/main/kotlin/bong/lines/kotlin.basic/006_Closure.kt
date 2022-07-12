package bong.lines.kotlin.basic

/**
 * const references = () => {
 *      var a = new change()
 *
 *      ~
 * }
 *
 * refernces();
 *  start : ref count - 1
 *
 *  end - method close -> ref count = 0
 *
 *  a 참조 값은 어디에서도 참조되지 않기 때문에 Garbage Collector 에 의해서 제거된다.
 */

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
