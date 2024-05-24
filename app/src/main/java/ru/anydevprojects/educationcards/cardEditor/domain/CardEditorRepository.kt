package ru.anydevprojects.educationcards.cardEditor.domain

import ru.anydevprojects.educationcards.domain.models.Card

interface CardEditorRepository {

    suspend fun getCardById(cardId: Long): Card

    suspend fun deleteCard(cardId: Long)

    suspend fun updateCard(cardId: Long, front: String, back: String)
}
