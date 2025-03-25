package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.ErrorCard
import tech.atlabs.githubchallenge.ui.composable.commons.LoadingIndicator
import tech.atlabs.githubchallenge.ui.composable.commons.SearchBar
import tech.atlabs.githubchallenge.ui.composable.user.UserCard
import tech.atlabs.githubchallenge.ui.utils.UiState
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun SearchScreen(viewModel: UserViewModel, onUserClick: (String) -> Unit) {

    val userState by viewModel.userState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var showBlankUsername by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.screen_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.search_spaced_items))
        ) {
            SearchScreenTitle()
            SearchBar(searchQuery, onQueryChanged = { searchQuery = it
                showBlankUsername = false}, onSearch = {
                if (searchQuery.isBlank()) {
                    showBlankUsername = true
                } else {
                    keyboardController?.hide()
                    viewModel.getUser(searchQuery)
                }
            })
            if (showBlankUsername) {
                ErrorCard(stringResource(R.string.error_empty_username))
            } else {
                SearchScreenContent(userState, onUserClick) { viewModel.getUser(searchQuery) }
            }
        }
    }
}

@Composable
private fun SearchScreenTitle() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = stringResource(R.string.search_title),
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun SearchScreenContent(
    userState: UiState<User>,
    onUserClick: (String) -> Unit,
    onRetry: () -> Unit
) {
    when (userState) {
        is UiState.Idle -> {}
        is UiState.Loading -> LoadingIndicator()
        is UiState.Success -> SearchSuccessState(userState.data, onUserClick)
        is UiState.Error -> ErrorCard(userState.message, onRetry = onRetry)
    }
}

@Composable
private fun SearchSuccessState(user: User, onUserClick: (String) -> Unit) {
    UserCard(user = user, onUserClick = onUserClick)
}