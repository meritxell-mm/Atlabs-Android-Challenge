package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubSurfaceColorDark

@Composable
fun Header(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GitHubSurfaceColorDark)
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp, 40.dp),
            text = title,
            style = GitHubChallengeTypography.titleLarge,
            color = Color.White
        )
    }
}