package router

import (
	"github.com/gin-gonic/gin"
	"go-jwt-restapi/middlewares"
	pinghttp "go-jwt-restapi/ping/delivery/http"
	tokenhttp "go-jwt-restapi/token/delivery/http"
	userhttp "go-jwt-restapi/user/delivery/http"
)

func InitRouter() *gin.Engine {
	router := gin.Default()
	api := router.Group("/api")
	{
		api.POST("/token", tokenhttp.GenerateToken)
		api.POST("/user/register", userhttp.RegisterUser)
		secured := api.Group("/secured").Use(middlewares.Auth())
		{
			secured.GET("/ping", pinghttp.Ping)
		}
	}
	return router
}
