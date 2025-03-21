package tech.atlabs.githubchallenge.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tech.atlabs.githubchallenge.data.entity.User
import tech.atlabs.githubchallenge.data.repository.RepoRepository
import tech.atlabs.githubchallenge.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val repoRepository: RepoRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun getUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = userRepository.getUser(username)
        }
    }

    fun getUserWithRepos(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getUser(username)
            val repos = repoRepository.getRepos(username)
            _user.value = user?.copy(repos = repos)
        }
    }
}
