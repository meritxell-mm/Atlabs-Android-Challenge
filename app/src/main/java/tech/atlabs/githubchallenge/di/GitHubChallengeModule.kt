package tech.atlabs.githubchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.atlabs.githubchallenge.data.repository.UserRepository
import tech.atlabs.githubchallenge.data.repository.UserRepositoryImpl
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService
import tech.atlabs.githubchallenge.data.remote.network.RetrofitClient
import tech.atlabs.githubchallenge.data.repository.RepoRepository
import tech.atlabs.githubchallenge.data.repository.RepoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubChallengeModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
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