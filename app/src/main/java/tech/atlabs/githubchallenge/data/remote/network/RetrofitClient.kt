package tech.atlabs.githubchallenge.data.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.atlabs.githubchallenge.BuildConfig
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService

object RetrofitClient {
    const val BASE_URL = "https://api.github.com/"
    const val USER_AGENT = "GitHubChallengeApp"
    private val GITHUB_TOKEN = "Bearer ${BuildConfig.GITHUB_TOKEN}"


    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("User-Agent", USER_AGENT)
                    .addHeader("Authorization", GITHUB_TOKEN)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    object ApiClient {
        val apiService: GitHubUserApiService by lazy {
            retrofit.create(GitHubUserApiService::class.java)
        }
    }
}
