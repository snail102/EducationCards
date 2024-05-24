package ru.anydevprojects.educationcards.cardEditor.data

import ru.anydevprojects.educationcards.cardEditor.domain.CardEditorRepository
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.domain.models.Card

class CardEditorRepositoryImpl(
    private val cardDao: CardDao
) : CardEditorRepository {
    override suspend fun getCardById(cardId: Long): Card {
        val cardEntity = cardDao.getCardById(cardId = cardId)

        return Card(
            id = cardEntity.id,
            front = cardEntity.front,
            back = cardEntity.back
        )
    }

    override suspend fun deleteCard(cardId: Long) {
        cardDao.deleteById(cardId = cardId)
    }

    override suspend fun updateCard(cardId: Long, front: String, back: String) {
        cardDao.updateById(cardId = cardId, front = front, back = back)
    }
}
