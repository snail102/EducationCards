package ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.statistics.cardsStatistics.domain.CardsStatisticsRepository
import ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation.models.EventCardsStatistics
import ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation.models.IntentCardsStatistics
import ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation.models.StateCardsStatistics

class CardsStatisticsViewModel(
    private val cardsStatisticsRepository: CardsStatisticsRepository
) :
    ViewModel(),
    MVI<StateCardsStatistics, IntentCardsStatistics, EventCardsStatistics> by mvi(
        StateCardsStatistics()
    ) {

    init {

        loadAllCardsStatistics()
    }

    private fun loadAllCardsStatistics() {
        viewModelScope.launch {
            val allStatistics = cardsStatisticsRepository.getAllCardsStatistics()
        }
    }
}
