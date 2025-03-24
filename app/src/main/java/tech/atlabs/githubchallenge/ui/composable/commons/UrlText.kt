package tech.atlabs.githubchallenge.ui.composable.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.ui.theme.AppBlueColor

@Composable
fun UrlText(url: String) {
    Text(
        text = url,
        style = MaterialTheme.typography.bodySmall,
        color = AppBlueColor,
        modifier = Modifier.padding(top = 12.dp)
    )
}