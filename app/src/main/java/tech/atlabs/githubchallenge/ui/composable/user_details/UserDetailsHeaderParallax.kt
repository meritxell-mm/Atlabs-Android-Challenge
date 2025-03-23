package tech.atlabs.githubchallenge.ui.composable.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tech.atlabs.githubchallenge.data.entity.User

@Composable
fun UserDetailsHeaderParallax (scrollState: LazyListState, user: User) {
        val scrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
        val parallaxOffset = (scrollOffset * 0.5f).coerceAtMost(100f)

        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = -parallaxOffset
                }
                .background(Color(0xFF0077FF))
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape)
            )
        }
    }
