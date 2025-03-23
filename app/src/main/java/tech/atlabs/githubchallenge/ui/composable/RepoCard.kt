package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallSplit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.data.entity.Repo
import tech.atlabs.githubchallenge.ui.theme.GitHubBkgColor
import tech.atlabs.githubchallenge.ui.theme.GitHubBlueColor
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubGreyColor

@Composable
fun RepoCard(repo: Repo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .border(
                width = 1.dp,
                color = GitHubGreyColor,
                shape = MaterialTheme.shapes.medium
            ), colors = CardDefaults.cardColors(
            containerColor = GitHubBkgColor
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = repo.name,
                    style = GitHubChallengeTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = GitHubBlueColor
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = GitHubGreyColor,
                            shape = MaterialTheme.shapes.medium
                        ).padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (repo.private) stringResource(R.string.repo_info_private) else stringResource(
                            R.string.repo_info_public
                        ),
                        style = GitHubChallengeTypography.labelSmall,
                        color = GitHubGreyColor
                    )
                }
            }

            repo.fullName?.let {
                Text(
                    text = stringResource(R.string.repo_info_forked_from, it),
                    style = GitHubChallengeTypography.bodySmall,
                    color = GitHubGreyColor,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            repo.description?.let {
                Text(
                    text = it,
                    style = GitHubChallengeTypography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repo.language?.let {
                    LanguageBadge(language = it)
                }

                IconText(
                    Icons.Default.Star,
                    stringResource(R.string.repo_info_stargazers),
                    repo.stargazersCount.toString()
                )
                IconText(
                    Icons.Default.CallSplit,
                    stringResource(R.string.repo_info_forks),
                    repo.forksCount.toString()
                )
            }

            Text(
                text = repo.htmlUrl,
                style = GitHubChallengeTypography.bodySmall,
                color = GitHubBlueColor,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Composable
fun LanguageBadge(language: String) {
    val color = when (language.lowercase()) {
        "shell" -> Color(0xFF89E051)
        "python" -> Color(0xFF3572A5)
        "html" -> Color(0xFFE34C26)
        "javascript" -> Color(0xFFF1E05A)
        else -> GitHubGreyColor
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = language,
            style = GitHubChallengeTypography.bodySmall
        )
    }
}