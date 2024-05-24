package ru.anydevprojects.educationcards.myDecks.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.domain.models.Deck
import ru.anydevprojects.educationcards.myDecks.domain.MyDecksRepository

class MyDecksRepositoryImpl(
    private val deckDao: DeckDao
) : MyDecksRepository {
    override fun getMyDecks(): Flow<List<Deck>> {
        return deckDao.getAllDecks().map {
            it.map { deckEntity ->
                Deck(
                    id = deckEntity.id,
                    name = deckEntity.name,
                    cards = emptyList()
                )
            }
        }
    }
}
