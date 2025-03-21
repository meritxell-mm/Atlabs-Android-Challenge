package tech.atlabs.githubchallenge.ui.navigation

sealed class NavScreenRoute(val route: String) {
    object Search : NavScreenRoute(NavRoutes.SEARCH)
    object UserDetail : NavScreenRoute("${NavRoutes.USER_DETAIL}/{${NavKeys.USERNAME}}")
}