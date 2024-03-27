package ru.anydevprojects.educationcards.deckViewer.domain

import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.educationcards.domain.models.Card
import ru.anydevprojects.educationcards.domain.models.DeckInfo

interface DeckViewerRepository {
    fun getDeckById(deckId: String): Flow<DeckInfo>

    fun getCardForDeck(deckId: String): Flow<List<Card>>
}
