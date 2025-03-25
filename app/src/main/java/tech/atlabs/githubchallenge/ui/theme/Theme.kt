package tech.atlabs.githubchallenge.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColors = lightColorScheme(
    primary = AppGreyColor,
    secondary = AppBlueColor,
    background = AppBkgColor,
    surface = AppSurfaceColor
)

private val DarkColors = darkColorScheme(
    primary = AppGreyColorDark,
    secondary = AppBlueColor,
    background = AppBkgColorDark,
    surface = AppSurfaceColorDark
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    isLandscape: Boolean = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE,
    content: @Composable () -> Unit
) {

    SetStatusBarColor(useDarkTheme)

    val colors = setThemeColors(useDarkTheme)

    MaterialTheme(
        colorScheme = colors,
        typography = appType(isDark = useDarkTheme, isLandscape = isLandscape),
        content = content
    )
}

@Composable
private fun setThemeColors(useDarkTheme: Boolean): ColorScheme {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }
    return colors
}

@Composable
private fun SetStatusBarColor(useDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    if (useDarkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = AppSurfaceColor, darkIcons = true
        )
    }
}