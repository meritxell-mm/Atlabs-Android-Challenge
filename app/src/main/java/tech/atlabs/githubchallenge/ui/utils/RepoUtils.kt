package tech.atlabs.githubchallenge.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.LoadingIndicator
import tech.atlabs.githubchallenge.ui.composable.repo.RepoCard

fun LazyListScope.addRepos(
    user: User,
    isLoadingMore: Boolean
) {
    user.repos?.takeIf { repos -> repos.isNotEmpty() }?.let { reposList ->
        items(reposList) { repo ->
            RepoCard(repo)
        }
        if (isLoadingMore) {
            item {
                LoadingIndicator()
            }
        }
        item {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_spacer)))
        }
    }
}