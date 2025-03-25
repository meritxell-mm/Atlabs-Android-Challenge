package tech.atlabs.githubchallenge.ui.composable.commons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import tech.atlabs.githubchallenge.R

@Composable
fun IconText(icon: ImageVector, iconDescription: String, value: String) {
    Row(
        modifier = Modifier.padding(dimensionResource(R.dimen.xsmall_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_spacer)))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}