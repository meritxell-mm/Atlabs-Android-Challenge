package tech.atlabs.githubchallenge.ui.composable.user

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.user.details.UserDetailsExtraInfo
import tech.atlabs.githubchallenge.ui.composable.user.details.UserDetailsStats
import tech.atlabs.githubchallenge.ui.composable.user.details.getExtraItems
import tech.atlabs.githubchallenge.ui.composable.user.details.header.UserDetailsHeader


@Composable
fun UserCard(user: User, onUserClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onUserClick(user.login) },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.medium_elevation)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val showStats = screenWidth > dimensionResource(R.dimen.min_screen_width)
        val isLandscape =
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscape) {
            UserDetailsLandscape(user, showStats)
        } else {
            UserDetailsPortrait(user, showStats)
        }
    }
}

@Composable
fun UserDetailsPortrait(user: User, showStats: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        UserDetailsHeader(user)
        if (showStats) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.xsmall_spacer)))
            UserDetailsStats(user, isLandscape = false)
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_spacer)))
        UserDetailsExtraInfo(getExtraItems(user = user))
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_spacer)))
    }
}

@Composable
fun UserDetailsLandscape(user: User, showStats: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.medium_padding))
                .padding(bottom = dimensionResource(R.dimen.xxlarge_padding)),
            verticalArrangement = Arrangement.Center
        ) {
            UserDetailsHeader(user)
            if (showStats) {
                UserDetailsStats(user, isLandscape = true)
            }
        }

        val extraItems = getExtraItems(user = user)

        extraItems?.let {
            VerticalDivider(
                thickness = dimensionResource(R.dimen.divider_thickness),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = dimensionResource(R.dimen.large_spacer))
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        start = dimensionResource(R.dimen.xxlarge_padding),
                        end = dimensionResource(R.dimen.medium_padding)
                    )
                    .padding(vertical = dimensionResource(R.dimen.large_spacer)),
            ) {
                UserDetailsExtraInfo(extraItems)
            }
        }
    }
}