package ru.anydevprojects.educationcards.profile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.anydevprojects.educationcards.core.navigation.Screens

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.isAvailableDeckStatistics) {
                ProfileSelectCard(
                    title = "Статистика по колодам",
                    onClick = {
                        navController.navigate(Screens.DecksStatisticsNavGraph.route)
                    }
                )
            }

            if (state.isAvailableCardsStatistics) {
                ProfileSelectCard(
                    title = "Статистика по картам",
                    onClick = {
                        navController.navigate(Screens.CardsStatisticsNavGraph.route)
                    }
                )
            }
        }
    }
}

@Composable
private fun ProfileSelectCard(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title)
        }
    }
}
