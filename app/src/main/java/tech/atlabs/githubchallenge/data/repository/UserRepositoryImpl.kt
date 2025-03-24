package tech.atlabs.githubchallenge.data.repository

import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService
import tech.atlabs.githubchallenge.domain.repository.UserRepository

class UserRepositoryImpl(private val apiService: GitHubUserApiService) : UserRepository {
    override suspend fun getUser(username: String): User {
        return apiService.getUser(username)
    }
}