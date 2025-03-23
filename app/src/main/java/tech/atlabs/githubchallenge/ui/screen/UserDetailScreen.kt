package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.ui.composable.RepoCard
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsExtraInfo
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsHeader
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsStats
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailScreen(viewModel: UserViewModel, username: String) {

    LaunchedEffect(username) {
        viewModel.getUserWithRepos(username)
    }

    val user by viewModel.user.collectAsState()

    user?.let {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {

            stickyHeader {
                UserDetailsHeader(user = it)
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                UserDetailsStats(it)
                Spacer(modifier = Modifier.height(12.dp))
                UserDetailsExtraInfo(it)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    text = stringResource(R.string.user_info_repos),
                    style = GitHubChallengeTypography.titleMedium
                )
            }

            it.repos?.takeIf { it.isNotEmpty() }?.let { reposList ->
                items(reposList) { repo ->
                    RepoCard(repo)
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            } ?: run {
                item {
                    Text(
                        stringResource(R.string.repo_info_empty),
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }
            }
        }
    } ?: run {
        // TODO gestió d'errors
        CircularProgressIndicator()
    }
}