package main

import (
	"go-jwt-restapi/database"
	"go-jwt-restapi/router"
)

func main() {
	// Initialize Database
	database.Connect("root:dream@tcp(127.0.0.1:3306)/dream?parseTime=true")
	database.Migrate()

	// Initialize Router
	routerServer := router.InitRouter()
	routerServer.Run(":8080")
}
