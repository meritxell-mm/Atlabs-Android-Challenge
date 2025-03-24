package tech.atlabs.githubchallenge.ui.composable.repo

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.ui.composable.commons.UrlText
import tech.atlabs.githubchallenge.ui.composable.repo.details.RepoHeader
import tech.atlabs.githubchallenge.ui.composable.repo.details.RepoInfo

@Composable
fun RepoCard(repo: Repo) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
                context.startActivity(intent)
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            RepoHeader(repo)
            RepoInfo(repo)
            UrlText(repo.htmlUrl)
        }
    }
}