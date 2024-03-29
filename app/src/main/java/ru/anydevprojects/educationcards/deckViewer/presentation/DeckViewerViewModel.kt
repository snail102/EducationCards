package ru.anydevprojects.educationcards.deckViewer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.deckViewer.domain.DeckViewerRepository
import ru.anydevprojects.educationcards.deckViewer.presentation.models.EventDeckViewer
import ru.anydevprojects.educationcards.deckViewer.presentation.models.IntentDeckViewer
import ru.anydevprojects.educationcards.deckViewer.presentation.models.StateDeckViewer

class DeckViewerViewModel(
    private val deckViewerRepository: DeckViewerRepository,
    private val deckId: String,
    private val deckName: String
) :
    ViewModel(),
    MVI<StateDeckViewer, IntentDeckViewer, EventDeckViewer> by mvi(
        StateDeckViewer(isLoading = true, name = deckName)
    ) {

    init {
        deckViewerRepository.getDeckById(deckId = deckId).onEach {
            updateState {
                copy(
                    isLoading = false,
                    name = it.name
                )
            }
        }.launchIn(viewModelScope)

        deckViewerRepository.getCardForDeck(deckId = deckId).onEach {
            updateState {
                copy(
                    isLoading = false,
                    cards = it
                )
            }
        }.launchIn(viewModelScope)
    }

    override fun onIntent(intent: IntentDeckViewer) {
    }
}
