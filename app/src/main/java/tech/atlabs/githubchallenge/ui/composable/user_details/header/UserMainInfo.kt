package tech.atlabs.githubchallenge.ui.composable.user_details.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.theme.AppBlueColor

@Composable
fun UserHeaderInfo(user: User) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = user.name ?: stringResource(R.string.user_info_no_name),
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "@${user.login}",
            style = MaterialTheme.typography.bodyMedium,
            color = AppBlueColor
        )

        Text(
            text = stringResource(R.string.user_info_joined, user.createdAtFormatted()),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}