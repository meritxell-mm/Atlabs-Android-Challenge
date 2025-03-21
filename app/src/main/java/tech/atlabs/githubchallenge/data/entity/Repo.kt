package tech.atlabs.githubchallenge.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    val id: Int,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val private: Boolean,
    @Json(name = "html_url")
    val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val language: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int,
    @Json(name = "forks_count")
    val forksCount: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String
)