package tech.atlabs.githubchallenge.ui.composable.repo

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
import androidx.compose.ui.res.dimensionResource
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.Repo
import tech.atlabs.githubchallenge.ui.composable.commons.UrlText
import tech.atlabs.githubchallenge.ui.composable.repo.details.RepoHeader
import tech.atlabs.githubchallenge.ui.composable.repo.details.RepoInfo
import tech.atlabs.githubchallenge.ui.utils.openUrl

@Composable
fun RepoCard(repo: Repo) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.xlarge_padding),
                vertical = dimensionResource(R.dimen.xsmall_padding)
            )
            .border(
                dimensionResource(R.dimen.border_thickness),
                MaterialTheme.colorScheme.primary,
                MaterialTheme.shapes.medium
            )
            .clickable {
                context.openUrl(repo.htmlUrl)
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.xlarge_padding))) {
            RepoHeader(repo)
            RepoInfo(repo)
            UrlText(repo.htmlUrl)
        }
    }
}