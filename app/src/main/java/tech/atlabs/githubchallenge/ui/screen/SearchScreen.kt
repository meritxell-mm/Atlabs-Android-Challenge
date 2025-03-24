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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.UserCard
import tech.atlabs.githubchallenge.ui.composable.commons.CustomLoadingIndicator
import tech.atlabs.githubchallenge.ui.composable.commons.ErrorCard
import tech.atlabs.githubchallenge.ui.composable.commons.SearchBar
import tech.atlabs.githubchallenge.ui.theme.AppTitleColor
import tech.atlabs.githubchallenge.ui.utils.UiState
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun SearchScreen(viewModel: UserViewModel, onUserClick: (String) -> Unit) {

    val userState by viewModel.userState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            SearchScreenTitle()
            SearchBarWithActions(searchQuery, onQueryChanged = { searchQuery = it }, onSearch = {
                viewModel.getUser(searchQuery)
            })
            SearchScreenContent(userState, onUserClick) { viewModel.getUser(searchQuery) }
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
        color = AppTitleColor
    )
}

@Composable
private fun SearchBarWithActions(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    SearchBar(
        searchQuery = searchQuery,
        onQueryChanged = onQueryChanged,
        onSearch = onSearch,
        modifier = Modifier.fillMaxWidth()
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
        is UiState.Loading -> CustomLoadingIndicator()
        is UiState.Success -> SearchSuccessState(userState.data, onUserClick)
        is UiState.Error -> ErrorCard(userState.message, onRetry = onRetry)
    }
}

@Composable
private fun SearchSuccessState(user: User, onUserClick: (String) -> Unit) {
    UserCard(user = user, onUserClick = onUserClick)
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    /*TODO  val mockViewModel = UserViewModel(FakeUserRepository())
      SearchScreen(viewModel = mockViewModel)*/
}