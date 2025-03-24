package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.RepoCard
import tech.atlabs.githubchallenge.ui.composable.commons.CustomLoadingIndicator
import tech.atlabs.githubchallenge.ui.composable.commons.ErrorCard
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsExtraInfo
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsHeader
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsStats
import tech.atlabs.githubchallenge.ui.theme.AppGrayLightColor
import tech.atlabs.githubchallenge.ui.utils.UiState
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, username: String) {

    LaunchedEffect(username) {
        viewModel.getUserWithRepos(username)
    }

    val userState by viewModel.userState.collectAsState()

    BaseScreen(backgroundColor = AppGrayLightColor) {
        UserDetailContent(
            userState = userState,
            onRetry = { viewModel.getUserWithRepos(username) })
    }
}

@Composable
fun UserDetailContent(userState: UiState<User>, onRetry: () -> Unit) {
    when (userState) {
        is UiState.Idle -> {}
        is UiState.Loading -> CustomLoadingIndicator()
        is UiState.Success -> UserDetailSuccess(user = userState.data)
        is UiState.Error -> ErrorCard(errorMessage = userState.message, onRetry = onRetry)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailSuccess(user: User) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        stickyHeader {
            UserDetailsHeader(user = user)
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            UserDetailsStats(user)
            Spacer(modifier = Modifier.height(12.dp))
            UserDetailsExtraInfo(user)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                text = stringResource(R.string.user_info_repos),
                style = MaterialTheme.typography.titleMedium
            )
        }

        user.repos?.takeIf { repos -> repos.isNotEmpty() }?.let { reposList ->
            items(reposList) { repo ->
                RepoCard(repo)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}