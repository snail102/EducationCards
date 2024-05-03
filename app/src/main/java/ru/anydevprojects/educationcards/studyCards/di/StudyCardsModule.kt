package ru.anydevprojects.educationcards.studyCards.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.studyCards.data.StudyCardsRepositoryImpl
import ru.anydevprojects.educationcards.studyCards.domain.StudyCardsRepository
import ru.anydevprojects.educationcards.studyCards.presentation.StudyCardsViewModel

val studyCardsModule = module {
    viewModel<StudyCardsViewModel> { StudyCardsViewModel(get(), get()) }
    factory<StudyCardsRepository> { StudyCardsRepositoryImpl(get()) }
}
