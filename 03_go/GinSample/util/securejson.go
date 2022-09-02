package util

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func SetSecureJson(router *gin.Engine) {
	router.GET("/someJSON", func(c *gin.Context) {
		names := []string{"lena", "austin", "foo"}

		// Will output  :   while(1);["lena","austin","foo"]
		c.SecureJSON(http.StatusOK, names)
	})
}
