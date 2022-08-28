package main

import (
	"GinSample/util"
	"github.com/gin-gonic/gin"
	"net/http"
)

func setupRouter() *gin.Engine {
	router := gin.Default()

	router.GET("/ping", func(context *gin.Context) {
		context.String(200, "pong")
	})

	router.GET("/albums", util.GetAlbums)

	router.GET("/albums/:id", util.GetAlbumByID)

	router.POST("/albums", util.PostAlbums)

	router.GET("/:name/:id", util.BindUrl)

	router.GET("/getb", util.GetDataB)

	router.GET("/getc", util.GetDataC)

	router.GET("/getd", util.GetDataD)

	router.GET("/testing", util.StartPage)

	router.GET("/someJSON", func(c *gin.Context) {
		data := map[string]interface{}{
			"lang": "GO语言",
			"tag":  "<br>",
		}

		// will output : {"lang":"GO\u8bed\u8a00","tag":"\u003cbr\u003e"}
		c.AsciiJSON(http.StatusOK, data)
	})

	return router
}

func main() {
	router := setupRouter()
	router.Run(":8080")
}
