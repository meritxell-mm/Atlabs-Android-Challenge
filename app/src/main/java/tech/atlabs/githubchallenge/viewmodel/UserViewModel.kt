package tech.atlabs.githubchallenge.viewmodel;

import android.util.Log
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
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private var currentPage = 1
    private val perPage = 30
    private var isLastPage = false

    private val _userState = MutableStateFlow<UiState<User>>(UiState.Idle)
    val userState: StateFlow<UiState<User>> = _userState

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore

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

    fun getUserWithRepos(username: String) {
        fetchUserData(username, getUserWithReposUseCase::invoke)
    }

    fun loadMoreRepos(username: String) {
        if (_isLoadingMore.value || isLastPage || _userState.value !is UiState.Success) return

        viewModelScope.launch {
            _isLoadingMore.value = true
            try {
                val newRepos = withContext(Dispatchers.IO) {
                    getReposUseCase(username, page = currentPage + 1, perPage = perPage)
                }

                val reposList = newRepos.getOrNull()
                if (!reposList.isNullOrEmpty()) {
                    currentPage++
                    val currentUser = (_userState.value as UiState.Success).data
                    val updatedUser = currentUser.copy(
                        repos = currentUser.repos.orEmpty() + reposList
                    )
                    _userState.value = UiState.Success(updatedUser)
                } else {
                    isLastPage = true
                }
            } catch (e: Exception) {
                Log.e("UserViewModel; loadMoreRepos", e.message.toString())
            } finally {
                _isLoadingMore.value = false
            }
        }
    }
}
