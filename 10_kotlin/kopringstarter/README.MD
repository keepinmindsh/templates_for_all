# @TestInstance
- PER_METHOD : test 함수 당 인스턴스가 생성된다.
- PER_CLASS : test 클래스 당 인스턴스가 생성된다.

```kotlin

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HtmlControllerTest(@Autowired val restTemplate: TestRestTemplate) {

   @BeforeAll
    fun setup(){
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        println(">> Assert blog page title, content and status code")
        var entity = restTemplate.getForEntity<String>("/")

        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>")
    }

//    @Test
//    fun `Assert article page title, content and status code`() {
//        println(">> TODO")
//    }

    @AfterAll
    fun teardown(){
        println(">> Tear Down")
    }
    
```

위와 같이 @TestInstance(TestInstance.Lifecycle.PER_CLASS) Annotation으로 라이프 사이클을 클래스 단위로 설정할 수 있다.
라이프 사이클을 클래스 단위로 지정해 놓으면 @BeforeAll, @AfterAll Annotation을 static method가 아닌 곳에서도 사용할 수 있다.
따라서 static method가 없는 kotlin에서도 위와 같이 일반 함수에 @BeforeAll, @AfterAll Annotation을 사용할 수 있다.

# Kotlin extension를 통해서 함수를 재정의 하는 방식 

```kotlin

fun LocalDateTime.format(): String = this.format(englishDateFormatter)

private val daysLookup = (1..31).associate { it.toLong() to getOrdinal(it) }

private val englishDateFormatter = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd")
    .appendLiteral(" ")
    .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
    .appendLiteral(" ")
    .appendPattern("yyyy")
    .toFormatter(Locale.ENGLISH)


import bong.lines.kopringstarter.blog.extensions.format

model["formatDate"] = LocalDateTime.now().format();

```

# Constructor 와 init


# JPA - 영속성 전이

- https://velog.io/@youns1121/JPA-object-references-an-unsaved-transient-instance-save-the-transient-instance-before-flushing-%EC%97%90%EB%9F%AC

- https://bottom-to-top.tistory.com/49?category=842783

- https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=rorean&logNo=221479709787