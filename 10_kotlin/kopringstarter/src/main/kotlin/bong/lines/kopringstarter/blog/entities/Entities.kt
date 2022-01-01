package bong.lines.kopringstarter.blog.entities

import bong.lines.kopringstarter.blog.extensions.toSlug
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Article(
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null)

@Entity
class User(
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null)