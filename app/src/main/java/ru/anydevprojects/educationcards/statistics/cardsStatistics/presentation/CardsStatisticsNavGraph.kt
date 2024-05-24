package ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.anydevprojects.educationcards.core.navigation.Screens

fun NavGraphBuilder.cardsStatisticsNavGraph(navController: NavController) {
    navigation(
        startDestination = Screens.CardsStatistics.route,
        route = Screens.CardsStatisticsNavGraph.route
    ) {
        composable(
            route = Screens.CardsStatistics.route
        ) {
            CardsStatisticsScreen()
        }
    }
}
