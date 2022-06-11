# Kotlin Basic 

### 코틀린 

코틀린은 자바 플랫폼에서 돌아가는 새로운 프로그래밍 언어다. 코틀린은 기존 자바 라이브러리나 프레임워크와 함께 잘 작동하며, 성능도 자바와 같은 수준이다.

### Kotlin 의 배경 

> [Kotlin 의 배경 - Wikipedia](https://ko.wikipedia.org/wiki/%EC%BD%94%ED%8B%80%EB%A6%B0_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4))

### 코틀릿 맛보기

```kotlin
package bong.lines.kotlin.basic

data class Person(val name : String,        // '데이터' 클래스 
                  var age : Int? = null)    // 널이 될 수 있는 타입(Int?)값과 파라미터 디폴트 값

fun main() { // 최상위 함수 
    val persons = listOf(Person("영희"), Person("철수", age = 20))  // 이름 붙인 파라미터 

    val oldest = persons.maxByOrNull { it.age ?: 0 }  // 람다 식과 엘비스 연산자
    println("나이가 가장 많은 사람 : $oldest ")            // 문자열 템플릿 
}
```