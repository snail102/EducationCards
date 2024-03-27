package ru.anydevprojects.educationcards.myDecks.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.myDecks.data.MyDecksRepositoryImpl
import ru.anydevprojects.educationcards.myDecks.domain.MyDecksRepository
import ru.anydevprojects.educationcards.myDecks.presentation.MyDecksViewModel

val myDecksModule = module {
    viewModel<MyDecksViewModel> { MyDecksViewModel(get(), get(), get()) }
    factory<MyDecksRepository> { MyDecksRepositoryImpl(get()) }
}
