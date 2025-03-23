package tech.atlabs.githubchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import tech.atlabs.githubchallenge.R

private val LightColors = lightColorScheme(
    primary = GitHubGreyColor,
    secondary = GitHubBlueBkgColor,
    background = GitHubBkgColor,
    surface = GitHubSurfaceColor
)

private val DarkColors = darkColorScheme(
    background = GitHubBkgColorDark,
    surface = GitHubSurfaceColorDark
)

@Composable
fun GitHubChallengeTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = GitHubChallengeTypography,
        content = content
    )
}