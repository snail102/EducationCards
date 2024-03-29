package ru.anydevprojects.educationcards.myDecks.presentation.models

sealed interface EventMyDecks {

    data object ShowImportNewDeck : EventMyDecks

    data class OpenDeck(val deckId: String, val deckName: String) : EventMyDecks

    data object ShowErrorIncorrectFileExtension : EventMyDecks
    data object ShowErrorGetSelectedFile : EventMyDecks
}
