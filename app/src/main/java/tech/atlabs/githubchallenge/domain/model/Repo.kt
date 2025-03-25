package tech.atlabs.githubchallenge.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    val id: Int,
    val name: String,
    val private: Boolean,
    @Json(name = "html_url")
    val htmlUrl: String,
    val description: String?,
    val language: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int,
    @Json(name = "forks_count")
    val forksCount: Int
)