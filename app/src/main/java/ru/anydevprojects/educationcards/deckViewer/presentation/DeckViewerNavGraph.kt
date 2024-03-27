package ru.anydevprojects.educationcards.deckViewer.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.anydevprojects.educationcards.core.navigation.Screens

fun NavGraphBuilder.deckViewerNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.DeckViewer.route,
        route = Screens.DeckViewerNavGraph.route
    ) {
        composable(route = Screens.DeckViewer.route) {
            DeckViewerScreen()
        }
    }
}
