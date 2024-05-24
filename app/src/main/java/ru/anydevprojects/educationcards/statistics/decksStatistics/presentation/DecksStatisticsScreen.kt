package ru.anydevprojects.educationcards.statistics.decksStatistics.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun DecksStatisticsScreen(viewModel: DecksStatisticsViewModel = koinViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
    }
}
