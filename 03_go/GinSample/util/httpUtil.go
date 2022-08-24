package util

import "github.com/gin-gonic/gin"

func HttpMethods() *gin.Engine {

	localRouter := gin.Default()

	localRouter.GET("/get")
	localRouter.POST("/post")
	localRouter.PUT("/put")
	localRouter.DELETE("/delete")
	localRouter.PATCH("/patch")
	localRouter.HEAD("/head")
	localRouter.OPTIONS("/options")

	return localRouter
}
