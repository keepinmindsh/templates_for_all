package model

import "time"

type Article struct {
	ID        int64     `json:"id"`
	Title     string    `json:"title"`
	Content   string    `json:"content"`
	UpdateAt  time.Time `json:"update_at"`
	CreatedAt time.Time `json:"created_at"`
}
