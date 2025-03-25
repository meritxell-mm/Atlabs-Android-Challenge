package tech.atlabs.githubchallenge.ui.composable.user.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.StatColumn
import tech.atlabs.githubchallenge.ui.composable.commons.StatRow

@Composable
fun UserDetailsStats(user: User, isLandscape: Boolean, screenWidth: Dp? = null) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .border(
                width = dimensionResource(R.dimen.border_thickness),
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {
        if (screenWidth != null && screenWidth < dimensionResource(R.dimen.min_screen_width)) {
            if (isLandscape) StatsInAColumn(user) else StatsInARow(user)
        } else {
            if (isLandscape) StatsInARow(user) else StatsInAColumn(user)
        }
    }
}

@Composable
private fun StatsInAColumn(user: User) {
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
private fun StatsInARow(user: User) {
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
