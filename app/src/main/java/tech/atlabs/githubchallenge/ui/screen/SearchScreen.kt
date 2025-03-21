package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import tech.atlabs.githubchallenge.ui.composable.Header
import tech.atlabs.githubchallenge.ui.composable.SearchBar
import tech.atlabs.githubchallenge.ui.composable.UserCard
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun SearchScreen(viewModel: UserViewModel) {

    val user by viewModel.user.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Header(stringResource(R.string.search_title))

        Column {
            SearchBar(
                searchQuery = searchQuery,
                onQueryChanged = { query ->
                    searchQuery = query
                },
                onSearch = {
                    viewModel.getUser(searchQuery)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 95.dp)
                    .padding(25.dp)
            )

            user?.let {
                UserCard(it)
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