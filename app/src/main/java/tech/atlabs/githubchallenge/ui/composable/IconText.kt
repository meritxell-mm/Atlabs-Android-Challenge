package tech.atlabs.githubchallenge.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTypography

@Composable
fun IconText(icon: ImageVector, iconDescription: String, value: String) {
    Row(
        modifier = Modifier.wrapContentSize().padding(8.dp), //TODO tot horizontal
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = value, //TODO clickable
            style = GitHubChallengeTypography.bodyMedium
        )
    }
}