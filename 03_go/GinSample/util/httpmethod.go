package util

import "github.com/gin-gonic/gin"

func HttpMethod(router *gin.Engine) {
	router.GET("/someGet", getting)
	router.POST("/somePost", posting)
	router.PUT("/somePut", putting)
	router.DELETE("/someDelete", deleting)
	router.PATCH("/somePatch", patching)
	router.HEAD("/someHead", head)
	router.OPTIONS("/someOptions", options)
}

func options(context *gin.Context) {

}

func head(context *gin.Context) {

}

func patching(context *gin.Context) {

}

func deleting(context *gin.Context) {

}

func putting(context *gin.Context) {

}

func posting(context *gin.Context) {

}

func getting(context *gin.Context) {

}
