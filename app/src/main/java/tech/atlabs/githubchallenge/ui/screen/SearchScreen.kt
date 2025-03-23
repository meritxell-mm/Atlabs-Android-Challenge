package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import tech.atlabs.githubchallenge.ui.composable.SearchBar
import tech.atlabs.githubchallenge.ui.composable.UserCard
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubTitleColor
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun SearchScreen(viewModel: UserViewModel, onUserClick: (String) -> Unit) {

    val user by viewModel.user.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            text = stringResource(R.string.search_title),
            style = GitHubChallengeTypography.titleLarge,
            color = GitHubTitleColor
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onQueryChanged = { query ->
                    searchQuery = query.trim()
                },
                onSearch = {
                    viewModel.getUser(searchQuery)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            user?.let {
                UserCard(it, onUserClick)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchScreenPreview() {
    /*TODO  val mockViewModel = UserViewModel(FakeUserRepository())
      SearchScreen(viewModel = mockViewModel)*/
}