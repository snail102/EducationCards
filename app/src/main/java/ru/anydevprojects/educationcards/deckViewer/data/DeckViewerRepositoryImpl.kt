package ru.anydevprojects.educationcards.deckViewer.data

import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.deckViewer.domain.DeckViewerRepository

class DeckViewerRepositoryImpl(
    private val deckDao: DeckDao,
    private val cardDao: CardDao
) : DeckViewerRepository
