package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubGreyColor
import tech.atlabs.githubchallenge.ui.theme.GitHubTitleColor


@Composable
fun StatColumn(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = GitHubChallengeTypography.bodyLarge)
        Text(text = label, style = GitHubChallengeTypography.titleSmall, color = GitHubGreyColor)
    }
}