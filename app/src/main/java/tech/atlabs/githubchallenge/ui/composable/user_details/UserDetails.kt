package tech.atlabs.githubchallenge.ui.composable.user_details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.domain.model.User

@Composable
fun UserDetails(user: User) {

    UserDetailsHeader(user)

    Spacer(modifier = Modifier.height(4.dp))

    UserDetailsStats(user)

    Spacer(modifier = Modifier.height(12.dp))

    UserDetailsExtraInfo(user)

    Spacer(modifier = Modifier.height(16.dp))
}