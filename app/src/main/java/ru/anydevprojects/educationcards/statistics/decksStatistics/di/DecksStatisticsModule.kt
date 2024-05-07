package ru.anydevprojects.educationcards.statistics.decksStatistics.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.statistics.decksStatistics.data.DecksStatisticsRepositoryImpl
import ru.anydevprojects.educationcards.statistics.decksStatistics.domain.DecksStatisticsRepository
import ru.anydevprojects.educationcards.statistics.decksStatistics.presentation.DecksStatisticsViewModel

val decksStatisticsModule = module {
    factory<DecksStatisticsRepository> { DecksStatisticsRepositoryImpl(get()) }
    viewModel { DecksStatisticsViewModel() }
}
