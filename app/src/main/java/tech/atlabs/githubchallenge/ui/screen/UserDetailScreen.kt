package tech.atlabs.githubchallenge.ui.screen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.ErrorCard
import tech.atlabs.githubchallenge.ui.composable.commons.LoadingIndicator
import tech.atlabs.githubchallenge.ui.composable.repo.ReposList
import tech.atlabs.githubchallenge.ui.composable.user.details.UserDetailsExtraInfo
import tech.atlabs.githubchallenge.ui.composable.user.details.UserDetailsStats
import tech.atlabs.githubchallenge.ui.composable.user.details.getExtraItems
import tech.atlabs.githubchallenge.ui.composable.user.details.header.UserDetailsHeader
import tech.atlabs.githubchallenge.ui.utils.UiState
import tech.atlabs.githubchallenge.ui.utils.addRepos
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, username: String) {

    val isLoadingMore by viewModel.isLoadingMore.collectAsState()

    LaunchedEffect(username) {
        viewModel.getUserWithRepos(username)
    }

    val userState by viewModel.userState.collectAsState()

    BaseScreen(backgroundColor = MaterialTheme.colorScheme.surface) {
        UserDetailContent(
            userState = userState,
            onRetry = { viewModel.getUserWithRepos(username) },
            isLoadingMore = isLoadingMore,
            onLoadMore = { viewModel.loadMoreRepos(username) })
    }
}

@Composable
fun UserDetailContent(
    userState: UiState<User>,
    onRetry: () -> Unit,
    isLoadingMore: Boolean,
    onLoadMore: () -> Unit
) {
    when (userState) {
        is UiState.Idle -> {}
        is UiState.Loading -> LoadingIndicator()
        is UiState.Success -> UserDetailSuccess(
            user = userState.data,
            isLoadingMore = isLoadingMore,
            onLoadMore = onLoadMore
        )

        is UiState.Error -> ErrorCard(errorMessage = userState.message, onRetry = onRetry)
    }
}

@Composable
fun UserDetailSuccess(user: User, isLoadingMore: Boolean, onLoadMore: () -> Unit) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    if (isLandscape) {
        UserDetailSuccessLandscape(
            user,
            screenWidth,
            isLoadingMore = isLoadingMore,
            onLoadMore = onLoadMore
        )
    } else {
        UserDetailSuccessPortrait(
            user,
            screenWidth,
            isLoadingMore = isLoadingMore,
            onLoadMore = onLoadMore
        )
    }
}

@Composable
fun UserDetailSuccessLandscape(
    user: User,
    screenWidth: Dp,
    isLoadingMore: Boolean,
    onLoadMore: () -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(
                    start = dimensionResource(R.dimen.xxlarge_padding),
                    end = dimensionResource(R.dimen.medium_padding)
                ), verticalArrangement = Arrangement.Center
        ) {
            item {
                Box(modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
                    context.startActivity(intent)
                }) {
                    UserDetailsHeader(user = user)
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.xxsmall_spacer)))
                UserDetailsStats(user, true, screenWidth = screenWidth)
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_spacer)))
                UserDetailsExtraInfo(getExtraItems(user = user))
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_spacer)))
            }
        }

        VerticalDivider(
            thickness = dimensionResource(R.dimen.divider_thickness),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.xlarge_padding))
        )

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .weight(1f)
                .padding(
                    start = dimensionResource(R.dimen.medium_padding),
                    end = dimensionResource(R.dimen.xxlarge_padding)
                )
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.xxlarge_padding),
                    vertical = dimensionResource(R.dimen.large_padding)
                ),
                text = stringResource(R.string.user_info_repos),
                style = MaterialTheme.typography.titleMedium
            )
            ReposList(user = user, isLoadingMore = isLoadingMore, onLoadMore = onLoadMore)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDetailSuccessPortrait(
    user: User,
    screenWidth: Dp,
    isLoadingMore: Boolean,
    onLoadMore: () -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisible != null && lastVisible >= totalItems - 3) {
                    onLoadMore()
                }
            }
    }

    LazyColumn(
        state = listState,
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
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_spacer)))
            UserDetailsStats(user, false, screenWidth)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_spacer)))
            UserDetailsExtraInfo(getExtraItems(user = user))
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_spacer)))
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.xxlarge_padding),
                    vertical = dimensionResource(R.dimen.large_padding)
                ),
                text = stringResource(R.string.user_info_repos),
                style = MaterialTheme.typography.titleMedium
            )
        }

        addRepos(user, isLoadingMore)
    }
}

