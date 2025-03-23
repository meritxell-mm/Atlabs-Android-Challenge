package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.data.entity.User
import tech.atlabs.githubchallenge.ui.composable.user_details.UserDetails
import tech.atlabs.githubchallenge.ui.theme.GitHubBkgColor
import tech.atlabs.githubchallenge.ui.theme.GitHubGrayBkgColor


@Composable
fun UserCard(user: User, onUserClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onUserClick(user.login) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = GitHubGrayBkgColor)
    ) {
        UserDetails(user)
    }
}