package ru.anydevprojects.educationcards.studyCards.data

import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.domain.models.Card
import ru.anydevprojects.educationcards.studyCards.domain.StudyCardsRepository

class StudyCardsRepositoryImpl(
    private val cardDao: CardDao
) : StudyCardsRepository {

    override suspend fun getCardsForStudyFromDeck(deckId: Long, countCards: Int): List<Card> {
        return cardDao.getSomeCardsByDeckId(deckId, countCards).map {
            Card(
                id = it.id,
                back = it.back,
                front = it.front
            )
        }
    }
}
