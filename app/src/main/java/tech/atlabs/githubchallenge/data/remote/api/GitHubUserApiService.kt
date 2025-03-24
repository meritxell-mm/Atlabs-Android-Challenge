package tech.atlabs.githubchallenge.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.domain.model.User

interface GitHubUserApiService {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): List<Repo>

}
