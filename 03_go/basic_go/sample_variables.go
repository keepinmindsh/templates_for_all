package main

import "fmt"

func variables() {
	const pi float64 = 3.1454356

	var (
		varA = 10
		varB = 20
	)

	fmt.Println(varA, varB)

	var Name string = "Hello World"

	fmt.Println(len(Name))

}
