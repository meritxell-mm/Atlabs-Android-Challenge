package tech.atlabs.githubchallenge.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import tech.atlabs.githubchallenge.ui.utils.toFormattedDate

/* The basic personal information required will be the avatar,
first and last name of the github user, username and biography.*/

@JsonClass(generateAdapter = true)
data class User(
    val login: String,
    val id: Int,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "html_url") val htmlUrl: String,
    val name: String?,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    @Json(name = "public_repos") val publicRepos: Int,
    @Json(name = "public_gists") val publicGists: Int,
    val followers: Int,
    val following: Int,
    @Json(name = "created_at") val createdAt: String,
    val repos: List<Repo>? = null
) {
    fun createdAtFormatted(): String {
        return createdAt.toFormattedDate()
    }
}
