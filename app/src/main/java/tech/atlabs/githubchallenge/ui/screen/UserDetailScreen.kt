package tech.atlabs.githubchallenge.ui.screen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.repo.RepoCard
import tech.atlabs.githubchallenge.ui.composable.commons.CustomLoadingIndicator
import tech.atlabs.githubchallenge.ui.composable.commons.ErrorCard
import tech.atlabs.githubchallenge.ui.composable.user.details.UserDetailsExtraInfo
import tech.atlabs.githubchallenge.ui.composable.user.details.header.UserDetailsHeader
import tech.atlabs.githubchallenge.ui.composable.user.details.UserDetailsStats
import tech.atlabs.githubchallenge.ui.composable.user.details.getExtraItems
import tech.atlabs.githubchallenge.ui.utils.UiState
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, username: String) {

    LaunchedEffect(username) {
        viewModel.getUserWithRepos(username)
    }

    val userState by viewModel.userState.collectAsState()

    BaseScreen(backgroundColor = MaterialTheme.colorScheme.surface) {
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

@Composable
fun UserDetailSuccess(user: User) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        UserDetailSuccessLandscape(user)
    } else {
        UserDetailSuccessPortrait(user)
    }
}

@Composable
fun UserDetailSuccessLandscape(user: User) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 10.dp)
        ) {
            item {
                Box(modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
                    context.startActivity(intent)
                }) {
                    UserDetailsHeader(user = user)
                }
                Spacer(modifier = Modifier.height(2.dp))
                UserDetailsStats(user)
                Spacer(modifier = Modifier.height(12.dp))
                UserDetailsExtraInfo(getExtraItems(user = user))
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        VerticalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end = 20.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                text = stringResource(R.string.user_info_repos),
                style = MaterialTheme.typography.titleMedium
            )
            user.repos?.takeIf { repos -> repos.isNotEmpty() }?.let { reposList ->
                LazyColumn {
                    items(reposList) { repo ->
                        RepoCard(repo)
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } ?: run {
                Text(
                    stringResource(R.string.repo_info_empty),
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailSuccessPortrait(user: User) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        stickyHeader {
            Box(modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
                context.startActivity(intent)
            }) {
                UserDetailsHeader(user = user)
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            UserDetailsStats(user)
            Spacer(modifier = Modifier.height(12.dp))
            UserDetailsExtraInfo(getExtraItems(user = user))
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