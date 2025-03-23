package tech.atlabs.githubchallenge.ui

import org.junit.Rule

/*TODO revisar
@get:Rule
val composeTestRule = createComposeRule()

@Test
fun testSearchScreen() {
    val mockViewModel = mockk<UserViewModel>(relaxed = true)
    val searchQuery = "testUser"

    composeTestRule.setContent {
        SearchScreen(viewModel = mockViewModel, onUserClick = { user ->
            navController.currentBackStackEntry?.savedStateHandle?.set("selectedUser", user)
            navController.navigate(NavScreenRoute.UserDetail.route)
        })
    }

    composeTestRule.onNodeWithText("Search").performClick() // Simula la cerca
    verify { mockViewModel.getUser(searchQuery) } // Verifica que es crida a la funció
}*/