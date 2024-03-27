package ru.anydevprojects.educationcards.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.anydevprojects.educationcards.core.navigation.Screens
import ru.anydevprojects.educationcards.deckViewer.presentation.deckViewerNavGraph
import ru.anydevprojects.educationcards.rootBottomNav.RootBottomScreen

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.BottomNavGraph.route) {
        composable(route = Screens.BottomNavGraph.route) {
            RootBottomScreen()
        }
        deckViewerNavGraph(navController = navController)
    }
}
