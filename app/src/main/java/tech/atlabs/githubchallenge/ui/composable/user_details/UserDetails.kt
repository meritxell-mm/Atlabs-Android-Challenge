package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.data.entity.User
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography

@Composable
fun UserDetails(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        UserDetailsHeader(user)

        Spacer(modifier = Modifier.height(4.dp))

        UserDetailsStats(user)

        Spacer(modifier = Modifier.height(4.dp))

        UserDetailsExtraInfo(user)
    }
}

@Composable
fun UserDetailsExtraInfo(user: User) {

    val extraInfo = listOf(
        Triple(Icons.Outlined.Apartment, R.string.user_info_company, user.company),
        Triple(Icons.Default.LocationOn, R.string.user_info_location, user.location),
        Triple(Icons.Default.Email, R.string.user_info_email, user.email),
        Triple(Icons.Default.Link, R.string.user_info_blog, user.blog),
        Triple(Icons.Default.Close, R.string.user_info_twitter, user.twitterUsername) //TODO canviar icona?
    )

    val items = extraInfo.filter { it.third?.isNotBlank() == true }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { (icon, iconDescription, value) ->
            IconText(
                icon = icon,
                iconDescription = stringResource(iconDescription),
                value = value.orEmpty()
            )
        }
    }
}

@Composable
fun UserDetailsStats(user: User) {
    Surface(
        color = Color(0xFFF6F8FF),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            StatColumn(
                label = stringResource(R.string.user_info_repositories),
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
    }
}

@Composable
private fun UserDetailsHeader(user: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = stringResource(R.string.user_avatar),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = user.name ?: stringResource(R.string.user_info_no_name),
                style = GitHubChallengeTypography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "@${user.login}",
                style = GitHubChallengeTypography.bodyMedium,
                color = Color(0xFF0077FF)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.user_info_joined, user.createdAtFormatted()),
                style = GitHubChallengeTypography.bodySmall,
                color = Color.Gray
            )
        }
    }

    Spacer(modifier = Modifier.height(2.dp))

    user.bio?.let {
        Text(
            text = user.bio,
            style = GitHubChallengeTypography.bodyMedium,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}