package tech.atlabs.githubchallenge.remote.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.atlabs.githubchallenge.remote.api.GitHubUserApiService

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
