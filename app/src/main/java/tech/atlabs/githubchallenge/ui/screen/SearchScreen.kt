package tech.atlabs.githubchallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.atlabs.githubchallenge.R
import tech.atlabs.githubchallenge.ui.composable.Header
import tech.atlabs.githubchallenge.ui.composable.SearchBar
import tech.atlabs.githubchallenge.ui.theme.GitHubBkgColor

@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Header(stringResource(R.string.search_title))

        SearchBar(
            searchQuery = "",
            onQueryChanged = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 95.dp)
                .padding(25.dp)
        )
    }
}

@Composable
@Preview
fun SearchScreenPreview() {
    SearchScreen()
}