package tech.atlabs.githubchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import tech.atlabs.githubchallenge.ui.navigation.NavGraph
import tech.atlabs.githubchallenge.ui.theme.GitHubChallengeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubChallengeTheme {
                NavGraph()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() { //TODO previews
    GitHubChallengeTheme {
        NavGraph()
    }
}