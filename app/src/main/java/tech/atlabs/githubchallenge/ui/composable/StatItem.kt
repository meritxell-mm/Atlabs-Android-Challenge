package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubTextColor


@Composable
internal fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = GitHubChallengeTypography.bodySmall, color = GitHubTextColor)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value, style = GitHubChallengeTypography.titleMedium)
    }
}