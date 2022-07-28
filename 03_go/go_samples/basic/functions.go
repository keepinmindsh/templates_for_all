package basic

import "fmt"

func Add(x, y int) int {
	return x * y
}

func Swap(x, y string) (string, string) {
	return y, x
}

func Split(sum int) (x, y int) {
	x = sum * 4 / 9
	y = sum - x
	return
}

var c, python, java bool

func ValueCheck() {
	var i int
	fmt.Println(i, c, python, java)
}
