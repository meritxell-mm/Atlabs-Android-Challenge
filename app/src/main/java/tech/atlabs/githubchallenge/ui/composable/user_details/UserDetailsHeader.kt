package tech.atlabs.githubchallenge.ui.composable.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.theme.AppBlueColor

@Composable
fun UserDetailsHeader(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(20.dp)
    ) {
        UserMainInfo(user)
        user.bio?.takeIf { it.isNotBlank() }?.let {
            Spacer(modifier = Modifier.height(16.dp))
            UserBio(it)
        }
    }
}

@Composable
private fun UserMainInfo(user: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        UserAvatar(user.avatarUrl)
        Spacer(modifier = Modifier.width(12.dp))
        UserTextInfo(user)
    }
}

@Composable
private fun UserAvatar(avatarUrl: String) {
    AsyncImage(
        model = avatarUrl,
        contentDescription = stringResource(R.string.user_info_avatar),
        modifier = Modifier
            .size(dimensionResource(R.dimen.avatar_size))
            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clip(CircleShape)
    )
}

@Composable
private fun UserTextInfo(user: User) {
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

@Composable
private fun UserBio(bio: String) {
    Text(
        text = bio,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxWidth()
    )
}
