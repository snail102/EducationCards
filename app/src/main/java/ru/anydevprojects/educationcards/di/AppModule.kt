package ru.anydevprojects.educationcards.di

import org.koin.dsl.module
import ru.anydevprojects.educationcards.database.di.databaseModule
import ru.anydevprojects.educationcards.deckViewer.di.deckViewerModule
import ru.anydevprojects.educationcards.importDeck.di.importDeckModule
import ru.anydevprojects.educationcards.myDecks.di.myDecksModule
import ru.anydevprojects.educationcards.profile.di.profileModule

val appModule = module {
    includes(myDecksModule, profileModule, importDeckModule, databaseModule, deckViewerModule)
}
