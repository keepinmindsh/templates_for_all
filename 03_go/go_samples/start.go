package main

import (
	"fmt"
	"go_samples/basic"
	"math/rand"
)

func mathRandom() {
	for i := 0; i < 10; i++ {
		println(rand.Intn(25))

		fmt.Printf("%d) %d\n", i, rand.Intn(25))
	}
}

func helloWorld() {
	fmt.Println("Test Call!")
	println("Hello World")
}

func main() {
	helloWorld()
	mathRandom()

	//common.Hello()
	//common.CommandsBuilder()

	basic.GoStringCannotHaveNil()

	basic.GoNilWithObject()

	basic.Add(30, 40)

	a, b := basic.Swap("Hello", "World")
	fmt.Println(a, b)

	x, y := basic.Split(50)
	fmt.Println(x, y)

	basic.ValueCheck()

	basic.Print()

	basic.TestPointer()
}
