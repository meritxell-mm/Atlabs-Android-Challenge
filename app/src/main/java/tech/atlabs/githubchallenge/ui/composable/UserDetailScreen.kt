package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography
import tech.atlabs.githubchallenge.ui.theme.GitHubTextColor
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel, username: String) {

    LaunchedEffect(username) {
        viewModel.getUser(username)
    }

    val user by viewModel.user.collectAsState()

    user?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = it.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.name ?: "", style = GitHubChallengeTypography.bodyMedium)
            Text(text = "@${it.login}", color = GitHubTextColor)
            it.bio?.let { bio ->
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = bio)
            }
        }
    } ?: run {
        // TODO gestió d'errors
        CircularProgressIndicator()
    }
}