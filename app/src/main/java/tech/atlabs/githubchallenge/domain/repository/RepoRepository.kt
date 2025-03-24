package tech.atlabs.githubchallenge.domain.repository

import tech.atlabs.githubchallenge.domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(username: String): List<Repo>
}