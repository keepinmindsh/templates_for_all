package main

import (
	"fmt"
	"go_samples/basic"
	"go_samples/common"
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

	common.Hello()
	common.CommandsBuilder()

	basic.Print()
}
