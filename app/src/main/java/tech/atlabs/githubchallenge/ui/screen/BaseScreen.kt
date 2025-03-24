package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    applySystemBarPadding: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    val systemBarInsets = if (applySystemBarPadding) {
        WindowInsets.systemBars.asPaddingValues()
    } else {
        PaddingValues(0.dp)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentWindowInsets = WindowInsets.systemBars,
        content = { innerPadding ->
            val topPaddingValues = PaddingValues(
                top = maxOf(
                    innerPadding.calculateTopPadding(),
                    systemBarInsets.calculateTopPadding()
                )
            )

            val bottomPaddingValues = PaddingValues(
                bottom = maxOf(
                    innerPadding.calculateBottomPadding(),
                    systemBarInsets.calculateBottomPadding()
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottomPaddingValues)
                    .background(backgroundColor)
                    .padding(topPaddingValues)
            ) {
                content()
            }
        }
    )
}
