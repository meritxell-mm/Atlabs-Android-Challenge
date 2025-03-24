package tech.atlabs.githubchallenge.domain.utils


sealed class ErrorType : Throwable() {
    sealed class UserErrorType : ErrorType() {
        object UserNotFound : UserErrorType()
        object NoReposFound : UserErrorType()
    }
}
