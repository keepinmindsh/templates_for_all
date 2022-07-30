package basic

import "fmt"

// NotPointerFunc Go는 프로그램내에서 값의 참조와 레코드를 전달할 수 있는 pointers를 지원합니다.
func NotPointerFunc(ival int) {
	ival = 0
}

func PointerFunc(iptr *int) {
	/*
		그에 반해 zeroptr는 int형 포인터를 받도록 *int 타입을 파라미터로 갖고 있습니다.
		함수안에서 *iptr는 메모리 주소에서 해당 주소의 현재값으로 포인터를 역참조(dereference) 합니다.
		역참조된 포인터에 값을 할당하면 이는 참조된 주소의 값을 바꿉니다
	*/
	*iptr = 0
}

func TestPointer() {
	i := 1
	fmt.Println("initial:", i)
	NotPointerFunc(i)
	fmt.Println("zeroval:", i)

	PointerFunc(&i)
	fmt.Println("zeroptr:", i)

	fmt.Println("pointer:", &i)
}
