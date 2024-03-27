package ru.anydevprojects.educationcards.myDecks.presentation.models

sealed interface EventMyDecks {

    data object ShowImportNewDeck : EventMyDecks

    data object OpenDeck : EventMyDecks

    data object ShowErrorIncorrectFileExtension : EventMyDecks
    data object ShowErrorGetSelectedFile : EventMyDecks
}
