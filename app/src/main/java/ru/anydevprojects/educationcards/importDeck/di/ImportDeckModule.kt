package ru.anydevprojects.educationcards.importDeck.di

import org.koin.dsl.module
import ru.anydevprojects.educationcards.importDeck.data.ImportDeckRepositoryImpl
import ru.anydevprojects.educationcards.importDeck.domain.ImportDeckRepository

val importDeckModule = module {
    factory<ImportDeckRepository> { ImportDeckRepositoryImpl(get(), get(), get()) }
}
