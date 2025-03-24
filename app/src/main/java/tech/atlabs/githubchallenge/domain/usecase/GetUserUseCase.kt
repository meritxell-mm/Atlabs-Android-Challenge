package tech.atlabs.githubchallenge.domain.usecase

import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.domain.repository.UserRepository
import tech.atlabs.githubchallenge.domain.utils.ErrorType

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String): Result<User?> {
        return try {
            val user = userRepository.getUser(username)
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(ErrorType.UserErrorType.UserNotFound)
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
