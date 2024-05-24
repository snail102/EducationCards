package ru.anydevprojects.educationcards.cardEditor.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.anydevprojects.educationcards.core.navigation.Screens

fun NavGraphBuilder.cardEditorNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.CardEditor.route,
        route = Screens.CardEditorNavGraph.route
    ) {
        composable(
            route = Screens.CardEditor.route,
            arguments = listOf(
                navArgument(Screens.CardEditor.cardIdArg) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getLong(
                Screens.CardEditor.cardIdArg
            ) ?: throw Exception("Empty argument cardId")

            CardEditorScreen(cardId = cardId, navController = navController)
        }
    }
}
