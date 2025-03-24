package tech.atlabs.githubchallenge.ui.composable.repo.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.ui.theme.AppBlueColor

@Composable
fun RepoHeader(repo: Repo) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = repo.name,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = AppBlueColor
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        RepoVisibilityTag(isPrivate = repo.private)
    }
}