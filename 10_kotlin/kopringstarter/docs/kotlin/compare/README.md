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

# 표준 출력 

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