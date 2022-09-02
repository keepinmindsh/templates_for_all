package util

import (
	"github.com/gin-gonic/gin"
	"log"
)

type PersonOnlyQuery struct {
	Name    string `form:"name"`
	Address string `form:"address"`
}

func OnlyBingQueryStringRouter(router *gin.Engine) {
	router.Any("/testing2", startPage)
}

func startPage(c *gin.Context) {
	var person PersonOnlyQuery
	if c.ShouldBindQuery(&person) == nil {
		log.Println("====== Only Bind By Query String ======")
		log.Println(person.Name)
		log.Println(person.Address)
	}
	c.String(200, "Success")
}
