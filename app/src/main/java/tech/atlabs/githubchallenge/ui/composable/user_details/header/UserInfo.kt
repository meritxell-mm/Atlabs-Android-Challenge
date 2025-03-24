package tech.atlabs.githubchallenge.ui.composable.user_details.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.domain.model.User

@Composable
fun UserMainInfo(user: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        UserAvatar(user.avatarUrl)
        Spacer(modifier = Modifier.width(12.dp))
        UserHeaderInfo(user)
    }
}