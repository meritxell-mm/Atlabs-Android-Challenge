package tech.atlabs.githubchallenge.ui.composable.repo.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallSplit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.ui.composable.commons.IconText


@Composable
fun RepoInfo(repo: Repo) {

    if (repo.isForked) {
        Text(
            text = stringResource(R.string.repo_info_forked_from, repo.fullName),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.xsmall_padding))
        )
    }
    repo.description?.let {
        Text(
            text = repo.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.small_padding))
        )
    }
    Row(
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.large_padding))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.large_spacer))
    ) {
        repo.language?.let { LanguageBadge(it) }

        IconText(
            icon = Icons.Default.Star,
            iconDescription = stringResource(R.string.icon_star),
            value = repo.stargazersCount.toString()
        )

        IconText(
            icon = Icons.Default.CallSplit,
            iconDescription = stringResource(R.string.icon_fork),
            value = repo.forksCount.toString()
        )
    }
}

