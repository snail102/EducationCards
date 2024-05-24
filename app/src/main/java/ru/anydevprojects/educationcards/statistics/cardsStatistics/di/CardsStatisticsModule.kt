package ru.anydevprojects.educationcards.statistics.cardsStatistics.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.statistics.cardsStatistics.data.CardsStatisticsRepositoryImpl
import ru.anydevprojects.educationcards.statistics.cardsStatistics.domain.CardsStatisticsRepository
import ru.anydevprojects.educationcards.statistics.cardsStatistics.presentation.CardsStatisticsViewModel

val cardsStatisticsModule = module {
    viewModel { CardsStatisticsViewModel(get()) }
    factory<CardsStatisticsRepository> { CardsStatisticsRepositoryImpl(get()) }
}
