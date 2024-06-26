package ru.anydevprojects.educationcards.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.anydevprojects.educationcards.cardEditor.presentation.cardEditorNavGraph
import ru.anydevprojects.educationcards.core.navigation.Screens
import ru.anydevprojects.educationcards.deckViewer.presentation.deckViewerNavGraph
import ru.anydevprojects.educationcards.rootBottomNav.RootBottomScreen
import ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation.cardsStatisticsNavGraph
import ru.anydevprojects.educationcards.statistics.decksStatistics.presentation.decksStatisticsNavGraph
import ru.anydevprojects.educationcards.studyCards.presentation.studyCardsNavGraph

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.BottomNavGraph.route) {
        composable(route = Screens.BottomNavGraph.route) {
            RootBottomScreen(rootNavController = navController)
        }
        deckViewerNavGraph(navController = navController)
        cardEditorNavGraph(navController = navController)
        studyCardsNavGraph(navController = navController)
        decksStatisticsNavGraph(navController = navController)
        cardsStatisticsNavGraph(navController = navController)
    }
}
