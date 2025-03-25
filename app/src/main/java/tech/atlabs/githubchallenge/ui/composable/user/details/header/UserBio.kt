package tech.atlabs.githubchallenge.ui.composable.user.details.header

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import tech.atlabs.githubchallenge.R

@Composable
fun UserBio(bio: String) {
    Text(
        text = bio,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.small_padding))
            .padding(bottom = dimensionResource(R.dimen.xsmall_padding))
    )
}
