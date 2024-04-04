package ru.anydevprojects.educationcards.cardEditor.data

import ru.anydevprojects.educationcards.cardEditor.domain.CardEditorRepository
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.domain.models.Card

class CardEditorRepositoryImpl(
    private val cardDao: CardDao
) : CardEditorRepository {
    override suspend fun getCardById(cardId: String): Card {
        val cardEntity = cardDao.getCardById(cardId = cardId)

        return Card(
            id = cardEntity.uid,
            front = cardEntity.front,
            back = cardEntity.back
        )
    }

    override suspend fun deleteCard(cardId: String) {
        cardDao.deleteById(cardId = cardId)
    }

    override suspend fun updateCard(cardId: String, front: String, back: String) {
        cardDao.updateById(cardId = cardId, front = front, back = back)
    }
}
