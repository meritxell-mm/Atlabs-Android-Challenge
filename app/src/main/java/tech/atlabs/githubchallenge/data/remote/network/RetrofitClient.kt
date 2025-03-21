package tech.atlabs.githubchallenge.data.remote.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService

object RetrofitClient {

    internal const val BASE_URL = "https://api.github.com/"

    val retrofit: Retrofit by lazy { //TODO authenticated requests
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    object ApiClient {
        val apiService: GitHubUserApiService by lazy {
            retrofit.create(GitHubUserApiService::class.java)
        }
    }
}
