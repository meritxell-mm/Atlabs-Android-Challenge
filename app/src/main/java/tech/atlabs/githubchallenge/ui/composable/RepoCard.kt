package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.data.entity.Repo
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubGrayBkgColor

@Composable
fun RepoCard(repo: Repo) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = repo.name,
                style = GitHubChallengeTypography.titleSmall,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = repo.fullName,
                style = GitHubChallengeTypography.bodySmall,
                color = GitHubGrayBkgColor //TODO repassar colors de tot
            )

            Spacer(modifier = Modifier.height(8.dp))

            repo.description?.let {
                Text(
                    text = it,
                    style = GitHubChallengeTypography.bodyMedium,
                    color = GitHubGrayBkgColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StatItem(
                    label = stringResource(R.string.repo_info_stargazers),
                    value = repo.stargazersCount.toString()
                )
                StatItem(
                    label = stringResource(R.string.repo_info_forks),
                    value = repo.forksCount.toString()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                repo.language?.let {
                    IconText(
                        icon = Icons.Default.Code,
                        iconDescription = stringResource(R.string.repo_info_language),
                        value = it
                    )
                }
                IconText(
                    icon = Icons.Default.Link, //TODO clickable
                    iconDescription = stringResource(R.string.repo_info_link),
                    value = repo.htmlUrl
                )
            }
        }
    }
}