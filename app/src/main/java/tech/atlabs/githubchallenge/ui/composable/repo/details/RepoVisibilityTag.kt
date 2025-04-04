package tech.atlabs.githubchallenge.ui.composable.repo.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R

@Composable
fun RepoVisibilityTag(isPrivate: Boolean) {
    val visibilityText = if (isPrivate)
        stringResource(R.string.repo_info_private)
    else
        stringResource(R.string.repo_info_public)

    Box(
        modifier = Modifier
            .border(dimensionResource(R.dimen.border_thickness), MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
            .padding(horizontal = dimensionResource(R.dimen.small_padding), vertical = dimensionResource(R.dimen.xsmall_padding))
    ) {
        Text(
            text = visibilityText,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}