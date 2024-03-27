package ru.anydevprojects.educationcards.myDecks.domain

import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.educationcards.domain.models.Deck

interface MyDecksRepository {

    fun getMyDecks(): Flow<List<Deck>>
}
