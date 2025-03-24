package tech.atlabs.githubchallenge.domain.usecase

import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.domain.repository.RepoRepository
import tech.atlabs.githubchallenge.domain.repository.UserRepository
import tech.atlabs.githubchallenge.domain.utils.ErrorType

class GetUserWithReposUseCase(
    private val userRepository: UserRepository,
    private val repoRepository: RepoRepository
) {
    suspend operator fun invoke(username: String): Result<User?> {
        return try {
            val userResponse = userRepository.getUser(username)
            val reposResponse = repoRepository.getRepos(username)

            if (userResponse != null) {
                val userWithRepos = userResponse.copy(repos = reposResponse)
                Result.success(userWithRepos)
            } else {
                Result.failure(ErrorType.UserErrorType.UserNotFound)
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}