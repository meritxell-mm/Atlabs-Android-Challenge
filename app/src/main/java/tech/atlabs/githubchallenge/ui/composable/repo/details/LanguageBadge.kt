package tech.atlabs.githubchallenge.ui.composable.repo.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.ui.utils.ProgrammingLangColor


@Composable
fun LanguageBadge(languageName: String) {
    val color =
        ProgrammingLangColor.fromName(languageName)?.color ?: MaterialTheme.colorScheme.primary

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.tag_size))
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.xsmall_spacer)))
        Text(
            text = languageName,
            style = MaterialTheme.typography.bodySmall
        )
    }
}