package ru.anydevprojects.educationcards.cardEditor.domain

import ru.anydevprojects.educationcards.domain.models.Card

interface CardEditorRepository {

    suspend fun getCardById(cardId: String): Card

    suspend fun deleteCard(cardId: String)

    suspend fun updateCard(cardId: String, front: String, back: String)
}
