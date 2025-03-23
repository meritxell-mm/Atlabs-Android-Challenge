package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.data.entity.Repo
import tech.atlabs.githubchallenge.ui.theme.GitHubBkgColor

@Composable
fun ReposList(repos: List<Repo>?) {
    repos?.takeIf { it.isNotEmpty() }?.let { reposList ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth().background(GitHubBkgColor),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(reposList) { repo ->
                RepoCard(repo)
            }
        }
    } ?: run {
        Text("No repositories found.")
    }
}