package ru.anydevprojects.educationcards.studyCards.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.anydevprojects.educationcards.core.navigation.Screens

fun NavGraphBuilder.studyCardsNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.StudyCards.route,
        route = Screens.StudyCardsNavGraph.route
    ) {
        composable(
            route = Screens.StudyCards.route,
            arguments = listOf(
                navArgument(Screens.StudyCards.deckIdArg) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getString(
                Screens.StudyCards.deckIdArg
            ) ?: throw Exception("Empty argument deckId")

            StudyCardsScreen(deckId = deckId, navController = navController)
        }
    }
}
