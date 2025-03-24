package tech.atlabs.githubchallenge.domain.repository

import tech.atlabs.githubchallenge.domain.model.User

interface UserRepository {
    suspend fun getUser(username: String): User?
}