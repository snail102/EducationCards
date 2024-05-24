package ru.anydevprojects.educationcards.myDecks.presentation.models

import android.net.Uri

sealed interface IntentMyDecks {

    data object OnImportNewDeckClick : IntentMyDecks

    data class OnDeckClick(val id: Long, val name: String) : IntentMyDecks

    data class SelectFile(val uri: Uri) : IntentMyDecks
}
