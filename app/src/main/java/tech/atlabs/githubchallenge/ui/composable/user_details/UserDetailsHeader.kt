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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.data.entity.User
import tech.atlabs.githubchallenge.ui.theme.GitHubBlueColor
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubGrayBkgColor
import tech.atlabs.githubchallenge.ui.theme.GitHubGreyColor

@Composable
fun UserDetailsHeader(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(GitHubGrayBkgColor, shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            AsyncImage(
                model = user.avatarUrl,
                contentDescription = stringResource(R.string.user_avatar),
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = GitHubGreyColor,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Text(
                    text = user.name ?: stringResource(R.string.user_info_no_name),
                    style = GitHubChallengeTypography.titleLarge
                )

                Text(
                    text = "@${user.login}",
                    style = GitHubChallengeTypography.bodyMedium,
                    color = GitHubBlueColor
                )

                Text(
                    text = stringResource(R.string.user_info_joined, user.createdAtFormatted()),
                    style = GitHubChallengeTypography.bodySmall,
                    color = GitHubGreyColor
                )
            }
        }

        user.bio?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = user.bio,
                style = GitHubChallengeTypography.bodyMedium,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}