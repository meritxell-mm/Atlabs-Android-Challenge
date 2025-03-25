package tech.atlabs.githubchallenge.ui.composable.user.details.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.domain.model.User

@Composable
fun UserDetailsHeader(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(bottomStart = dimensionResource(R.dimen.medium_radius), bottomEnd = dimensionResource(R.dimen.medium_radius))
            )
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {
        UserMainInfo(user)
        user.bio?.takeIf { it.isNotBlank() }?.let {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.large_spacer)))
            UserBio(it)
        }
    }
}