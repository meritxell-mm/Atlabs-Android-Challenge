package tech.atlabs.githubchallenge.ui.composable.repo

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.LoadingIndicator
import tech.atlabs.githubchallenge.ui.utils.addRepos

@Composable
fun ReposList(user: User, isLoadingMore: Boolean, onLoadMore: () -> Unit) {
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

    LazyColumn(state = listState) {
        addRepos(user, isLoadingMore)
    }
}
