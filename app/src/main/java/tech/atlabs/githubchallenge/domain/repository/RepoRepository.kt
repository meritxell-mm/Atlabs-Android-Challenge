package tech.atlabs.githubchallenge.domain.repository

import tech.atlabs.githubchallenge.domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(username: String, page: Int = 1, perPage: Int = 30): List<Repo>
}