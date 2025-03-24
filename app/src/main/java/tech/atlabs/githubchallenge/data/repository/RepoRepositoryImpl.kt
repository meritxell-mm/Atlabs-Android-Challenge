package tech.atlabs.githubchallenge.data.repository

import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.data.remote.api.GitHubUserApiService
import tech.atlabs.githubchallenge.domain.repository.RepoRepository

class RepoRepositoryImpl(private val apiService: GitHubUserApiService) : RepoRepository {
    override suspend fun getRepos(username: String): List<Repo> {
        return apiService.getRepos(username)
    }
}