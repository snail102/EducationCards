package ru.anydevprojects.educationcards.cardEditor.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.cardEditor.data.CardEditorRepositoryImpl
import ru.anydevprojects.educationcards.cardEditor.domain.CardEditorRepository
import ru.anydevprojects.educationcards.cardEditor.presentation.CardEditorViewModel

val cardEditorModule = module {
    factory<CardEditorRepository> { CardEditorRepositoryImpl(get()) }
    viewModel<CardEditorViewModel> { CardEditorViewModel(get(), get()) }
}
