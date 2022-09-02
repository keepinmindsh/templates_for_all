package util

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func Redirects(router *gin.Engine) {
	router.GET("/test1", func(c *gin.Context) {
		c.Redirect(http.StatusMovedPermanently, "http://www.google.com/")
	})

	router.GET("/test2", func(c *gin.Context) {
		c.Request.URL.Path = "/test2"
		router.HandleContext(c)
	})

	router.GET("/test3", func(c *gin.Context) {
		c.JSON(200, gin.H{"hello": "world"})
	})
}
