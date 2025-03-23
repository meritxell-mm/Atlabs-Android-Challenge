package tech.atlabs.githubchallenge.ui.composable.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.data.entity.User
import tech.atlabs.githubchallenge.ui.composable.StatColumn
import tech.atlabs.githubchallenge.ui.theme.GitHubBkgColor
import tech.atlabs.githubchallenge.ui.theme.GitHubGreyColor

@Composable
fun UserDetailsStats(user: User) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                width = 1.dp,
                color = GitHubGreyColor,
                shape = MaterialTheme.shapes.medium
            )
            .background(GitHubBkgColor, shape = MaterialTheme.shapes.medium)
            .padding(12.dp)
    ) {
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
}
