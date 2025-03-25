package tech.atlabs.githubchallenge.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
    } ?: item {
        Text(
            text = stringResource(R.string.repo_info_empty),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.xxlarge_padding), vertical = dimensionResource(R.dimen.small_padding)),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}