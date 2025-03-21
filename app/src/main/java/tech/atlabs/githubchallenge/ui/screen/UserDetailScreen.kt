package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.ui.composable.ReposList
import tech.atlabs.githubchallenge.ui.composable.UserDetailedInfo
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, username: String) {

    LaunchedEffect(username) {
        viewModel.getUserWithRepos(username)
    }

    val user by viewModel.user.collectAsState()

    user?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserDetailedInfo(it)
            ReposList(it.repos)
        }
    } ?: run {
        // TODO gestió d'errors
        CircularProgressIndicator()
    }
}