package ru.anydevprojects.educationcards.deckViewer.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.jsoup.Jsoup
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.deckViewer.domain.DeckViewerRepository
import ru.anydevprojects.educationcards.domain.models.Card
import ru.anydevprojects.educationcards.domain.models.DeckInfo

class DeckViewerRepositoryImpl(
    private val deckDao: DeckDao,
    private val cardDao: CardDao
) : DeckViewerRepository {
    override fun getDeckById(deckId: String): Flow<DeckInfo> {
        return deckDao.findByIdFlow(id = deckId).flowOn(Dispatchers.IO).map {
            DeckInfo(
                id = it.uid,
                name = it.name
            )
        }
    }

    override fun getCardForDeck(deckId: String): Flow<List<Card>> {
        return cardDao.getAllCardByDeckIdFlow(deckId = deckId).flowOn(Dispatchers.IO).map { cards ->
            cards.map {
                Card(
                    id = it.uid,
                    front = Jsoup.parse(it.front).text(),
                    back = it.back
                )
            }
        }
    }
}
