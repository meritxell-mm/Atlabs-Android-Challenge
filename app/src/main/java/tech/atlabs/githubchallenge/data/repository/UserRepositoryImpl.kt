package tech.atlabs.githubchallenge.data.repository

import tech.atlabs.githubchallenge.data.entity.User
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService

class UserRepositoryImpl(private val apiService: GitHubUserApiService) : UserRepository {
    override suspend fun getUser(username: String): User {
        return apiService.getUser(username)
    }
}