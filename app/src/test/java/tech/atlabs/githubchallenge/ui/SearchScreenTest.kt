package tech.atlabs.githubchallenge.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import tech.atlabs.githubchallenge.ui.navigation.NavScreenRoute
import tech.atlabs.githubchallenge.ui.screen.SearchScreen
import tech.atlabs.githubchallenge.viewmodel.UserViewModel

//TODO revisar
@get:Rule
val composeTestRule = createComposeRule()

@Test
fun testSearchScreen() {
    val mockViewModel = mockk<UserViewModel>(relaxed = true)
    val searchQuery = "testUser"

    composeTestRule.setContent {
        SearchScreen(viewModel = mockViewModel, onClick = { user ->
            navController.currentBackStackEntry?.savedStateHandle?.set("selectedUser", user)
            navController.navigate(NavScreenRoute.UserDetail.route)
        })
    }

    composeTestRule.onNodeWithText("Search").performClick() // Simula la cerca
    verify { mockViewModel.getUser(searchQuery) } // Verifica que es crida a la funció
}