package ru.anydevprojects.educationcards.studyCards.domain

import ru.anydevprojects.educationcards.domain.models.Card

interface StudyCardsRepository {

    suspend fun getCardsForStudyFromDeck(deckId: String, countCards: Int): List<Card>
}
