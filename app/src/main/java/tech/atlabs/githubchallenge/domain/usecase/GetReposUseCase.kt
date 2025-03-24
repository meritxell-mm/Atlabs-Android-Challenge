package tech.atlabs.githubchallenge.domain.usecase

import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.domain.repository.RepoRepository
import tech.atlabs.githubchallenge.domain.utils.ErrorType

class GetReposUseCase(
    private val repoRepository: RepoRepository
) {
    suspend operator fun invoke(username: String): Result<List<Repo>> {
        return try {
            val repos = repoRepository.getRepos(username)
            if (repos != null) {
                Result.success(repos)
            } else {
                Result.failure(ErrorType.UserErrorType.NoReposFound)
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
