package tech.atlabs.githubchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tech.atlabs.githubchallenge.ui.screen.SearchScreen
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubChallengeTheme {
                SearchScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    GitHubChallengeTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Hello, Compose!")
        }
    }
}