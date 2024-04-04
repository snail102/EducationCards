package ru.anydevprojects.educationcards.studyCards.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.studyCards.domain.StudyCardRepository
import ru.anydevprojects.educationcards.studyCards.presentation.models.EventStudyCards
import ru.anydevprojects.educationcards.studyCards.presentation.models.IntentStudyCards
import ru.anydevprojects.educationcards.studyCards.presentation.models.StateStudyCards

class StudyCardsViewModel(
    private val deckId: String,
    private val studyCardRepository: StudyCardRepository
) :
    ViewModel(),
    MVI<StateStudyCards, IntentStudyCards, EventStudyCards> by mvi(StateStudyCards()) {

    init {
        studyCardRepository
    }
    override fun onIntent(intent: IntentStudyCards) {
        when (intent) {
            IntentStudyCards.OnEditCurrentCardClick -> openCardEditor()
        }
    }

    private fun openCardEditor() {
        viewModelScope.launch {
            emitEvent(
                EventStudyCards.OpenCardEditor()
            )
        }
    }
}
