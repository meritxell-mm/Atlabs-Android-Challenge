package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun UserDetailedInfo(user: User) {
    AsyncImage(
        model = user.avatarUrl,
        contentDescription = stringResource(R.string.user_avatar),
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = user.name ?: stringResource(R.string.user_info_no_name),
        style = GitHubChallengeTypography.headlineSmall //TODO tbd
    )

    Text(
        text = "@${user.login}",
        style = GitHubChallengeTypography.bodyMedium,
        color = Color.Gray
    )

    user.bio?.let {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = it,
            style = GitHubChallengeTypography.bodyMedium
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        StatItem(
            label = stringResource(R.string.user_info_repositories),
            value = user.publicRepos.toString()
        )
        StatItem(
            label = stringResource(R.string.user_info_followers),
            value = user.followers.toString()
        )
        StatItem(
            label = stringResource(R.string.user_info_following),
            value = user.following.toString()
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    user.company?.let {
        IconText(
            icon = Icons.Default.Business,
            iconDescription = stringResource(R.string.user_info_company),
            value = it
        )
    }

    user.location?.let {
        IconText(
            icon = Icons.Default.LocationOn,
            iconDescription = stringResource(R.string.user_info_location),
            value = it
        )
    }

    user.blog?.let {
        IconText(
            icon = Icons.Default.Link,
            iconDescription = stringResource(R.string.user_info_blog),
            value = it
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
}