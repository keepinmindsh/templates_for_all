package bong.lines.kopringstarter.blog.model

import java.time.LocalDateTime

data class ArticleRQ(
    var title: String,
    var headline: String,
    var content: String,
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String,
    var slug: String,
    var addedAt: LocalDateTime = LocalDateTime.now()
)