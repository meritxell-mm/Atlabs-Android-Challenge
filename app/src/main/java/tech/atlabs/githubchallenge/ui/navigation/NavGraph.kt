package tech.atlabs.githubchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.atlabs.githubchallenge.ui.composable.UserDetailScreen
import tech.atlabs.githubchallenge.ui.screen.SearchScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreenRoute.Search.route) {
        composable(NavScreenRoute.Search.route) {
            SearchScreen(
                viewModel = hiltViewModel(),
                onUserClick = { username ->
                    navController.navigate("${NavRoutes.USER_DETAIL}/${username}")
                }
            )
        }
        composable(NavScreenRoute.UserDetail.route) { backStackEntry ->
            val username = backStackEntry.arguments?.getString(NavKeys.USERNAME)
            username?.let {
                UserDetailScreen(viewModel = hiltViewModel(), username = it)
            }
        }
    }
}