package main

import "net/http"

func httpCall() {

	// Get Mapping - / 로 표기
	http.HandleFunc("/", func(rw http.ResponseWriter, r *http.Request) {
		rw.Write([]byte("hello"))
	})

	http.ListenAndServe(":8080", nil)
}
