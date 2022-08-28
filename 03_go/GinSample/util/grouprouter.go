package util

import "github.com/gin-gonic/gin"

func settingGroupRouter(router *gin.Engine) {
	router.Group("/v1")
	{
		router.GET("/getb", GetDataB)
		router.GET("/getc", GetDataC)
		router.GET("/getd", GetDataD)
	}
}
