# Package definition / import

### Java

Package 의 정의는 반드시 소스 상단에 정의되어야 한다.

### Kotlin 

Package 의 정의는 반드시 소스 상단에 정의되어야 한다.

# Main 진입점 

### Java

##### Java 의 메인 진입점은 main 함수로 정의되고, 정의 방식은 아래와 같다.

```java

class Sample {
    public static void main(String[] args) {
        System.out.println("Main 진입점");
    }
}

```


### Kotlin

##### Kotlin 의 메인 진입점은 main 함수로 정의되고, 정의 방식은 아래와 같다.

```kotlin

fun main() {
    println("Hello world!")
}

```

```kotlin

fun main(args: Array<String>) {
    println(args.contentToString())
}

```

##### Kotlin Coroutine

동시성 프로그래밍의 개념을 코틀린에 도입한 것을 코루틴이라고 합니다. 

- 코루틴은 코루틴이 시작된 스레드를 중단하지 않으면서 비동기적으로 실행되는 코드 입니다. 
- 복잡한 다수 스레드 관리를 직접해주지 않아도되며, 기존 다중 스레드 보다 훨씬 더 효율적으로 동작합니다.

```kotlin

suspend fun main() = coroutineScope {
    for (i in 0 until  10) {
        launch {
            delay(1000L - i * 10)
            print("$i ")
        }
    }
}

```

# 표준 출력 - Output

### Java 

```java

class Sample{
    public static void main(String[] args) {
        System.out.println("표준 출력");
    }
}

```

### Kotlin 

```kotlin

package bong.lines.basic.standard_output

fun main(){
    standardOutputPrint()
}

fun standardOutputPrint() {
    print("Hello ")
    print("World! ")
}

```

# 표준 입력 - Input 

### Java

```java
public class Sample {
    public static void standardInputTest() throws Exception {
        InputStream inputStream = System.in;
        int inputValue = inputStream.read();
        
        log.info("Input Value : {}", inputValue);
    }
}

```

```java
public class Sample {
    public static void standardInputTest() throws Exception {
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String bufferedContent = bufferedReader.readLine();

        log.info("Input Value : {}", bufferedContent);
    }
}
```

### Kotlin 

```kotlin

private fun standardReadLineToInt() {
    val numberInput = readLine()!!.toInt()
    println("입력 받은 내용(Int): $numberInput")
}

private fun standardReadLine() {
    val input = readLine()
    println("입력 받은 내용: $input")
}

private fun standardMultiReadLine(){
    val s = Scanner(System.`in`)
    val a = s.nextInt()
    val n = s.nextInt()
    s.nextLine()
    val line = s.nextLine()
    val arr = line.split(" ")

    for(it in arr){
        if(it.toInt() < n)
            print("$it ")
    }
}

private fun standardReadFile(){
    val path = "/Users/lines/sources/01_bonggit/templates_for_all/10_kotlin/kopringstarter/docs/kotlin/compare/README.md"
    val file = File(path)
    val inputStream: InputStream = file.inputStream()
    val text = inputStream.bufferedReader().use { it.readText() }
    println(text)
}

```

# 함수 - Functions 

### Java

```java

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

public class JavaMoney {
    public static void main(String[] args) {
        // Normal function
        getLength(); 
        
        // Interface Lambda 
        Function<String, Integer> func = x -> x.length();
        Integer apply = func.apply("Bong Lines");
        System.out.println(apply);

        // Interface Lambda 
        Function<String, Integer> func = x -> x.length();
        Function<Integer, Integer> func2 = x -> x * 2;
        Integer result = func.andThen(func2).apply("Bong Lines"); 
        System.out.println(result);

        JavaMoney obj = new JavaMoney();
        // Method Reference 
        List<String> result = obj.map(list, obj::sha256);
        result.forEach(System.out::println);
    }

    public <T, R> List<R> map(List<T> list, Function<T, R> func) {

        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(func.apply(t));
        }
        return result;

    }

    public String sha256(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public Integer getLength(String str) {
        return str.length();
    }
}
```


### Kotlin

Kotlin 함수는 키워드 fun 을 사용하여 선언된다. 

```kotlin
fun double(x: Int): Int {
    return 2 * x
}

fun main(){
    val result = double(2)

    print(result)
}
```

##### Parameters 정의 

 변수에 대한 Type을 지정한다. 

```kotlin
fun powerOf(number : Int, exponent : Int) : Int {
    return number * exponent
}

fun main(){
    val result = powerOf(10, 10)
    
    print(result)
}
```

###### Default Parameter 지정 

 함수에서 각 파라미터의 기본 값을 설정할 수 있다. 값이 인자를 통해서 전달되지 않을 경우 기본 값을 세팅되어 사용된다.  
 기본 값으로 설정된 경우에는 해당 함수가 호출될 때의 파라미터 인자가 정의되어 있지 않아도 사용 가능하다. 

```kotlin
fun read(
    b: ByteArray,
    off: Int = 0,
    len: Int = b.size
) : Int {
    return off * len
}

fun main(){
    print(read(
        "value".toByteArray(),
        10
    ))
}
```

##### 함수 Override 

Class를 상속한 메소드를 재정의 할 때는 Default Parameter 는 사용할 수 없음.

```kotlin
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

fun main(){
    println(B().foo(300))
}
```
##### Named Arguments 

 함수 내의 파라미터 명에 대해서 지정하여 사용 가능하다. 

```kotlin
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

fun main(){
    reformat(
        "Value",
        upperCaseFirstLetter = false
    )
}
```

##### Unit 를 반환하는 함수 

어떤 함수가 유용한 값을 반환하지 않은 경우, 반환 타입은 Unit 다.  
Unit 반환 타입에 대한 선언은 선택적으로 선언하거나 선언하지 않아도 된다.  

```kotlin
fun printHello(name: String?): Unit {
    if(name != null){
        println("Hello $name")
    }else{
        println("Hi There!")
    }
}

fun main(){
    printHello("Value")
}
```

##### Single-Expression 함수 

 Function 에 대해서 block body 없이 사용할 수 있다. 

```kotlin

fun ourDream(x : Int) : Int = x * 50

fun main(){
    println(ourDream(50))
}
```

##### Variable Number 인자 

vararg 를 이용한 다수 파라미터에 대한 인자 전달이 가능합니다. 

```kotlin
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for(t in ts)
        result.add(t)
    return result
}

fun main(){
    println(asList(1,2,3,4,5,6,7,8,9,10))
}
```

##### Infix 정의

- Infix function 은 멤버 함수 이거나 확장 함수 여야 한다. 
- Infix function 단일 파라미터를 가진다. 
- Infix function 는 기본 값과 다수 파라미터 인자 전달은 할 수 없다. 

```kotlin

infix fun Int.plus(x: Int): Int {
    return this + x
}

infix fun String.substringMatches(r: Regex) : List<String> {
    return r.findAll(this)
        .map { it.value }
        .toList()
}

fun main(){
    println(10 plus 5)

    val matches = "a bc def" substringMatches ".*? ".toRegex()
    println(matches)
}

```

##### Function Scope / Closure

 - Closure
   - 클로저(Closure)는 outer scope(상위 함수의 영역)의 변수를 접근할 수 있는 함수를 말합니다.
   - 코틀린은 클로저를 지원하며 그렇기 때문에 익명함수는 함수 밖에서 정의된 변수에 접근할 수 있습니다.

```kotlin
fun add(x: Int): (Int) -> Int {
    return fun(y: Int): Int {
        return x + y
    }
}

fun main(){
 val func = add(10)
 
 func(20)
 
 val closureValue = func(20)
 println(closureValue)
}
```

##### Higher-order 함수 

고차 함수는 함수를 파라미터(인자)로써 가질 수 있고, 함수를 반환할 수 있는 함수를 말한다. 

```kotlin

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

fun main(){
    higherOrderFunction()
}
```

# 변수 - Variables 

### 1 bit -> 1 Byte

1 Byte is equal to 8 Bit (eight bit)  
1 Bit is equal to 0.125 Byte (zero point one hundred and twenty-five b)  
1 Byte is equal to 8 bits (eight bits)  
1 Bit is equal to 1 bits (one bits)  
1 Byte is equal to 8 Bit (eight bit)  

### Java

int A 32-bit ( 4-byte ) integer value  
short A 16-bit ( 2-byte ) integer value  
long A 64-bit ( 8-byte ) integer value  
byte An 8-bit ( 1-byte ) integer value  
float A 32-bit ( 4-byte ) integer value  
double A 64-bit ( 8-byte ) integer value  
char A 16-bit character using the Unicode encoding scheme  
boolean  A true or false value  

### Kotlin 

- 코틀린에서는 기초 타입이 없고 모든 타입이 객체 형태이다.
- 타입 클래스에 정의된 기초 함수와 프로퍼티를 사용할 수 있다.
- Any는 코틀린의 최상위 클래스이기 때문에 어떤 타입의 데이터도 대입할 수 있다.
- 타입 종류: Int, Double, Float, Long, Short, Byte, Char, Boolean, String, Any, Unit, Nothing

```kotlin

val a: Int = 1  // immediate assignment
val b = 2   // `Int` type is inferred
val c: Int  // Type required when no initializer is provided
c = 3       // deferred assignment

```


