package tech.atlabs.githubchallenge.ui.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsExtraInfo
import tech.atlabs.githubchallenge.ui.composable.user_details.header.UserDetailsHeader
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetailsStats
import tech.atlabs.githubchallenge.ui.composable.user_details.getExtraItems


@Composable
fun UserCard(user: User, onUserClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onUserClick(user.login) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        val isLandscape =
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (isLandscape) {
            UserDetailsLandscape(user)
        } else {
            UserDetailsPortrait(user)
        }
    }
}

@Composable
fun UserDetailsPortrait(user: User) {

    UserDetailsHeader(user)

    Spacer(modifier = Modifier.height(4.dp))

    UserDetailsStats(user)

    Spacer(modifier = Modifier.height(12.dp))

    UserDetailsExtraInfo(getExtraItems(user=user))

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun UserDetailsLandscape(user: User) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(horizontal = 10.dp)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            UserDetailsHeader(user)
            UserDetailsStats(user)
        }

        val extraItems = getExtraItems(user = user)

        extraItems?.let {
            VerticalDivider(thickness = 2.dp, modifier = Modifier.padding(vertical = 16.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 10.dp)
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                UserDetailsExtraInfo(extraItems)
            }
        }
    }
}