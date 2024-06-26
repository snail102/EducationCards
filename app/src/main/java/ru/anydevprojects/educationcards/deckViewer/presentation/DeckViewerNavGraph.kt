package ru.anydevprojects.educationcards.deckViewer.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.anydevprojects.educationcards.core.navigation.Screens

fun NavGraphBuilder.deckViewerNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.DeckViewer.route,
        route = Screens.DeckViewerNavGraph.route
    ) {
        composable(
            route = Screens.DeckViewer.route,
            arguments = listOf(
                navArgument(Screens.DeckViewer.deckIdArg) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getLong(
                Screens.DeckViewer.deckIdArg
            ) ?: throw Exception("Empty argument deckId")

            val deckName = backStackEntry.arguments?.getString(
                Screens.DeckViewer.deckNameArg
            ) ?: throw Exception("Empty argument deckName")

            DeckViewerScreen(deckId = deckId, deckName = deckName, navController = navController)
        }
    }
}
