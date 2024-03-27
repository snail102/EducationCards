package ru.anydevprojects.educationcards.importDeck.domain

import android.net.Uri

interface ImportDeckRepository {

    suspend fun importDeckFromFile(uri: Uri): Result<Unit>
}
