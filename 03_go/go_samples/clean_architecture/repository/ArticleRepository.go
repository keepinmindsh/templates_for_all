package repository

import "go_samples/clean_architecture/model"

type ArticleRepository interface {
	GetById(id int64) (*model.Article, error)
	GetByTitle(title string) (*model.Article, error)
	Update(article *model.Article) (*model.Article, error)
	Delete(id int64) (bool, error)
}
