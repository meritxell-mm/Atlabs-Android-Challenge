package tech.atlabs.githubchallenge.ui.composable.user_details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.StatColumn
import tech.atlabs.githubchallenge.ui.composable.commons.StatRow

@Composable
fun UserDetailsStats(user: User) {

    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {
        if (isLandscape) {
            UserDetailsStatsLandscape(user)
        } else {
            UserDetailsStatsPortrait(user)
        }
    }
}

@Composable
private fun UserDetailsStatsPortrait(user: User) {
    StatColumn(
        label = stringResource(R.string.user_info_repos),
        value = user.publicRepos.toString()
    )
    StatColumn(
        label = stringResource(R.string.user_info_followers),
        value = user.followers.toString()
    )
    StatColumn(
        label = stringResource(R.string.user_info_following),
        value = user.following.toString()
    )
}

@Composable
private fun UserDetailsStatsLandscape(user: User) {
    StatRow(
        label = stringResource(R.string.user_info_repos),
        value = user.publicRepos.toString()
    )
    StatRow(
        label = stringResource(R.string.user_info_followers),
        value = user.followers.toString()
    )
    StatRow(
        label = stringResource(R.string.user_info_following),
        value = user.following.toString()
    )
}
