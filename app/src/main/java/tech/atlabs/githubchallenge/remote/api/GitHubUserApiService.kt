package tech.atlabs.githubchallenge.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import tech.atlabs.githubchallenge.data.entity.User

interface GitHubUserApiService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User
}
