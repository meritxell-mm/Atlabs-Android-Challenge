package tech.atlabs.githubchallenge.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.domain.usecase.GetReposUseCase
import tech.atlabs.githubchallenge.domain.usecase.GetUserUseCase
import tech.atlabs.githubchallenge.domain.usecase.GetUserWithReposUseCase
import tech.atlabs.githubchallenge.ui.utils.ErrorHandler
import tech.atlabs.githubchallenge.ui.utils.UiState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserWithReposUseCase: GetUserWithReposUseCase,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _userState = MutableStateFlow<UiState<User>>(UiState.Idle)
    val userState: StateFlow<UiState<User>> = _userState

    private fun fetchUserData(username: String, useCase: suspend (String) -> Result<User?>) {
        viewModelScope.launch {
            _userState.value = UiState.Loading
            try {
                val result = withContext(Dispatchers.IO) {
                    useCase(username)
                }
                val user = result.getOrNull()

                if (user != null) {
                    _userState.value = UiState.Success(user)
                } else {
                    val errorMessage =
                        errorHandler.getErrorMessage(result.exceptionOrNull() ?: Exception())
                    _userState.value = UiState.Error(errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = errorHandler.getErrorMessage(e)
                _userState.value = UiState.Error(errorMessage)
            }
        }
    }

    fun getUser(username: String) {
        fetchUserData(username, getUserUseCase::invoke)
    }

    fun getUserWithRepos( username: String) {
        fetchUserData(username, getUserWithReposUseCase::invoke)
    }
}
