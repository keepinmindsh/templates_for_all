package bong.lines.kopringstarter.blog

import bong.lines.kopringstarter.blog.entities.Article
import bong.lines.kopringstarter.blog.entities.User
import bong.lines.kopringstarter.blog.extensions.format
import bong.lines.kopringstarter.blog.model.ArticleRQ
import bong.lines.kopringstarter.blog.repositories.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Controller
class HtmlController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["formatDate"] = LocalDateTime.now().format();
        model["title"] = "Blog"
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    @PostMapping("/article")
    fun saveArticle(@RequestBody articleRQ: ArticleRQ) : ResponseEntity<String> {



        repository.save(Article(
            articleRQ.title,
            articleRQ.headline,
            articleRQ.content,
            User(
                articleRQ.login,
                articleRQ.firstname,
                articleRQ.lastname,
                articleRQ.description
            ),
            articleRQ.slug,
            articleRQ.addedAt
        ))

        return ResponseEntity("success" ,HttpStatus.OK)
    }

    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String)


}