package tech.atlabs.githubchallenge.data.repository

import tech.atlabs.githubchallenge.data.entity.Repo
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService

class RepoRepositoryImpl(private val apiService: GitHubUserApiService) : RepoRepository {
    override suspend fun getRepos(username: String): List<Repo> {
        return apiService.getRepos(username)
    }
}