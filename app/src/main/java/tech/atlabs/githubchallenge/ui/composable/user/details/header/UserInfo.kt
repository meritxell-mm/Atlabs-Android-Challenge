package tech.atlabs.githubchallenge.ui.composable.user.details.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User
import tech.atlabs.githubchallenge.ui.composable.commons.Pic

@Composable
fun UserMainInfo(user: User) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Pic(user.avatarUrl)
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_spacer)))
        UserHeaderInfo(user)
    }
}