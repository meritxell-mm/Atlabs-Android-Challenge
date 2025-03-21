package tech.atlabs.githubchallenge.data.repository

import tech.atlabs.githubchallenge.data.entity.User

interface UserRepository {
    suspend fun getUser(username: String): User?
}