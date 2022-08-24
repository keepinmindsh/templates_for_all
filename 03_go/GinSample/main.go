package main

import "github.com/gin-gonic/gin"

func setupRouter() *gin.Engine {
	router := gin.Default()
	router.GET("/ping", func(context *gin.Context) {
		context.String(200, "pong")
	})
	return router
}

func main() {
	router := setupRouter()
	router.Run(":8080")
}
