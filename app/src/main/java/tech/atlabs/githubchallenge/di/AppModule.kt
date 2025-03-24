package tech.atlabs.githubchallenge.di

import android.content.Context
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
import tech.atlabs.githubchallenge.data.repository.RepoRepositoryImpl
import tech.atlabs.githubchallenge.data.repository.UserRepositoryImpl
import tech.atlabs.githubchallenge.domain.repository.RepoRepository
import tech.atlabs.githubchallenge.domain.repository.UserRepository
import tech.atlabs.githubchallenge.domain.usecase.GetReposUseCase
import tech.atlabs.githubchallenge.domain.usecase.GetUserUseCase
import tech.atlabs.githubchallenge.domain.usecase.GetUserWithReposUseCase
import tech.atlabs.githubchallenge.ui.utils.ResourceProvider
import tech.atlabs.githubchallenge.ui.utils.ResourceProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserWithReposUseCase(
        userRepository: UserRepository,
        repoRepository: RepoRepository
    ): GetUserWithReposUseCase {
        return GetUserWithReposUseCase(userRepository, repoRepository)
    }

    @Provides
    @Singleton
    fun provideGetReposUseCase(
        repoRepository: RepoRepository
    ): GetReposUseCase {
        return GetReposUseCase(repoRepository)
    }

    @Provides
    @Singleton
    fun provideContext(application: android.app.Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

}