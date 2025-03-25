package tech.atlabs.githubchallenge.ui.composable.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextDecoration
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.ui.utils.isLink
import tech.atlabs.githubchallenge.ui.utils.openUrl

@Composable
fun IconText(icon: ImageVector, iconDescription: String, value: String) {
    val context = LocalContext.current
    val isUrl = remember(value) { isLink(value) }

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
            color = if (isUrl) MaterialTheme.colorScheme.secondary else LocalContentColor.current,
            textDecoration = if (isUrl) TextDecoration.Underline else TextDecoration.None,
            modifier = if (isUrl) Modifier.clickable { context.openUrl(value) } else Modifier
        )
    }
}