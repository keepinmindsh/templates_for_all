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


