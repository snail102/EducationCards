package ru.anydevprojects.educationcards.cardEditor.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.educationcards.cardEditor.domain.CardEditorRepository
import ru.anydevprojects.educationcards.cardEditor.presentation.models.EventCardEditor
import ru.anydevprojects.educationcards.cardEditor.presentation.models.IntentCardEditor
import ru.anydevprojects.educationcards.cardEditor.presentation.models.StateCardEditor
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi

class CardEditorViewModel(
    private val cardId: String,
    private val cardEditorRepository: CardEditorRepository
) : ViewModel(),
    MVI<StateCardEditor, IntentCardEditor, EventCardEditor> by mvi(
        StateCardEditor(isLoading = true)
    ) {

    init {
        viewModelScope.launch {
            val card = cardEditorRepository.getCardById(cardId = cardId)
            updateState {
                copy(
                    isLoading = false,
                    front = card.front,
                    back = card.back
                )
            }
        }
    }

    override fun onIntent(intent: IntentCardEditor) {
    }
}
