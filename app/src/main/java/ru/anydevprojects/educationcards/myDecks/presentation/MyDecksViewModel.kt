package ru.anydevprojects.educationcards.myDecks.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.importDeck.domain.ImportDeckRepository
import ru.anydevprojects.educationcards.myDecks.domain.MyDecksRepository
import ru.anydevprojects.educationcards.myDecks.presentation.models.EventMyDecks
import ru.anydevprojects.educationcards.myDecks.presentation.models.IntentMyDecks
import ru.anydevprojects.educationcards.myDecks.presentation.models.StateMyDecks

class MyDecksViewModel(
    private val applicationContext: Context,
    // todo replace on usecase
    private val importDeckRepository: ImportDeckRepository,
    private val myDecksRepository: MyDecksRepository
) :
    ViewModel(),
    MVI<StateMyDecks, IntentMyDecks, EventMyDecks> by mvi(StateMyDecks(isLoading = true)) {

    init {
        myDecksRepository.getMyDecks().onStart {
            updateState {
                copy(isLoading = true)
            }
        }.onEach {
            updateState {
                copy(isLoading = false, myDecks = it)
            }
        }.launchIn(viewModelScope)
    }

    override fun onIntent(intent: IntentMyDecks) {
        when (intent) {
            IntentMyDecks.OnImportNewDeckClick -> showImportDeck()
            is IntentMyDecks.OnDeckClick -> openDeck(intent.id, intent.name)
            is IntentMyDecks.SelectFile -> importDeck(intent.uri)
        }
    }

    private fun showImportDeck() {
        viewModelScope.launch {
            emitEvent(EventMyDecks.ShowImportNewDeck)
        }
    }

    private fun openDeck(deckId: String, deckName: String) {
        viewModelScope.launch {
            emitEvent(EventMyDecks.OpenDeck(deckId = deckId, deckName = deckName))
        }
    }

    private fun importDeck(uri: Uri) {
        viewModelScope.launch {
            importDeckRepository.importDeckFromFile(uri)
        }
    }
}
