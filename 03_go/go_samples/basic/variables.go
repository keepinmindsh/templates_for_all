package basic

import "fmt"

func Variables() {
	// 1. string 변수 선언
	var text string

	// 2. 선언과 동시에 초기화하기
	var text2 string = "123"

	// 3. 타입을 지정하지 않고 초기화하기
	var text3 = "Hello"

	// 4. := 연산자를 사용하여 var 생략하기
	text4 := "World"

	// 5. 값 변경하기
	text2 = "456"

	// 6. 변수 여러개 한번에 정의하기
	// var i, j, k int

	// 7. 복수 변수 한번에 초기화하기 (값 입력한 순서대로 초기화)
	var i, j, k int = 1, 2, 3

	fmt.Println(text + text2 + text3 + text4)
	fmt.Println(i + j + k)

	// 1. 변수명은 문자와 숫자로만
	// 2. 단, 숫자로 시작할 수 없음 (문자 혹은 _ 로 시작)
	// 3. 예약어, 키워드는 변수명으로 사용할 수 없다
	// 3번 https://thebook.io/006806/ch02/02/02/

	// 가장 중요한 것
	// 변수명은 의미있게 짓기
	// 변수명만 봐도 이 변수가 어떤 역할을 할 지 알 수 있도록 만들어야 좋은 변수랍니다
	// 변수명 짓기 - https://www.curioustore.com/#!/
}
