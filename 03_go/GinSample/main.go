package main

import (
	"GinSample/util"
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
	"time"
)

func setupRouter() *gin.Engine {
	gin.ForceConsoleColor()

	router := gin.Default()

	// LoggerWithFormatter middleware will write the logs to gin.DefaultWriter
	// By default gin.DefaultWriter = os.Stdout
	router.Use(gin.LoggerWithFormatter(func(param gin.LogFormatterParams) string {
		// your custom format
		return fmt.Sprintf("%s - [%s] \"%s %s %s %d %s \"%s\" %s\"\n",
			param.ClientIP,
			param.TimeStamp.Format(time.RFC1123),
			param.Method,
			param.Path,
			param.Request.Proto,
			param.StatusCode,
			param.Latency,
			param.Request.UserAgent(),
			param.ErrorMessage,
		)
	}))

	util.TemplateMapper(router)

	//router.Use(util.Logger())

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

	s := &http.Server{
		Addr:           ":8080",
		Handler:        router,
		ReadTimeout:    10 * time.Second,
		WriteTimeout:   10 * time.Second,
		MaxHeaderBytes: 1 << 20,
	}
	s.ListenAndServe()
}
