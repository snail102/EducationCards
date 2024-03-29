package ru.anydevprojects.educationcards.deckViewer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.deckViewer.data.DeckViewerRepositoryImpl
import ru.anydevprojects.educationcards.deckViewer.domain.DeckViewerRepository
import ru.anydevprojects.educationcards.deckViewer.presentation.DeckViewerViewModel

val deckViewerModule = module {
    factory<DeckViewerRepository> { DeckViewerRepositoryImpl(get(), get()) }
    viewModel<DeckViewerViewModel> { DeckViewerViewModel(get(), get(), get()) }
}
