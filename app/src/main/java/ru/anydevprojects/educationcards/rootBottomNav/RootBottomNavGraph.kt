package ru.anydevprojects.educationcards.rootBottomNav

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.anydevprojects.educationcards.core.navigation.Screens
import ru.anydevprojects.educationcards.myDecks.presentation.MyDecksScreen
import ru.anydevprojects.educationcards.profile.presentation.ProfileScreen

@Composable
fun RootBottomNavGraph(navController: NavHostController, rootNavController: NavHostController) {
    NavHost(
        navController = navController,
        route = Screens.BottomNavGraph.route,
        startDestination = Screens.MyDecks.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }
    ) {
        composable(route = Screens.MyDecks.route) {
            MyDecksScreen(navController = rootNavController)
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen()
        }
    }
}
