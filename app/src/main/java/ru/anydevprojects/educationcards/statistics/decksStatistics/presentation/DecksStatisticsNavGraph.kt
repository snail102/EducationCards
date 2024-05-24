package ru.anydevprojects.educationcards.statistics.decksStatistics.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.anydevprojects.educationcards.core.navigation.Screens

fun NavGraphBuilder.decksStatisticsNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.DecksStatistics.route,
        route = Screens.DecksStatisticsNavGraph.route
    ) {
        composable(
            route = Screens.DecksStatistics.route
        ) {
            DecksStatisticsScreen()
        }
    }
}
