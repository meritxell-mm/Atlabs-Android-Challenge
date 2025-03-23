package tech.atlabs.githubchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.atlabs.githubchallenge.BuildConfig
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService
import tech.atlabs.githubchallenge.data.remote.network.RetrofitClient
import tech.atlabs.githubchallenge.data.repository.RepoRepository
import tech.atlabs.githubchallenge.data.repository.RepoRepositoryImpl
import tech.atlabs.githubchallenge.data.repository.UserRepository
import tech.atlabs.githubchallenge.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubChallengeModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("User-Agent", RetrofitClient.USER_AGENT)
                    .addHeader("Authorization", BuildConfig.GITHUB_TOKEN)
                    .build()
                chain.proceed(request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GitHubUserApiService {
        return retrofit.create(GitHubUserApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: GitHubUserApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(apiService: GitHubUserApiService): RepoRepository {
        return RepoRepositoryImpl(apiService)
    }


}