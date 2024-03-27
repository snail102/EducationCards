package ru.anydevprojects.educationcards.deckViewer.presentation

import androidx.lifecycle.ViewModel
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.deckViewer.domain.DeckViewerRepository
import ru.anydevprojects.educationcards.deckViewer.presentation.models.EventDeckViewer
import ru.anydevprojects.educationcards.deckViewer.presentation.models.IntentDeckViewer
import ru.anydevprojects.educationcards.deckViewer.presentation.models.StateDeckViewer

class DeckViewerViewModel(
    private val deckViewerRepository: DeckViewerRepository
) :
    ViewModel(),
    MVI<StateDeckViewer, IntentDeckViewer, EventDeckViewer> by mvi(
        StateDeckViewer(isLoading = true)
    ) {
    override fun onIntent(intent: IntentDeckViewer) {
    }
}
