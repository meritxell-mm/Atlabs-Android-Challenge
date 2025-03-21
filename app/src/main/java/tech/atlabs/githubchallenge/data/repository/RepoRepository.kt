package tech.atlabs.githubchallenge.data.repository

import tech.atlabs.githubchallenge.data.entity.Repo

interface RepoRepository {
    suspend fun getRepos(username: String): List<Repo>
}