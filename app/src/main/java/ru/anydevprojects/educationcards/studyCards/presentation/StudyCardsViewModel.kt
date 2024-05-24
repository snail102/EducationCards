package ru.anydevprojects.educationcards.studyCards.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedrejeb.richeditor.model.RichTextState
import kotlinx.coroutines.launch
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.domain.models.Card
import ru.anydevprojects.educationcards.studyCards.domain.StudyCardsRepository
import ru.anydevprojects.educationcards.studyCards.presentation.models.EventStudyCards
import ru.anydevprojects.educationcards.studyCards.presentation.models.IntentStudyCards
import ru.anydevprojects.educationcards.studyCards.presentation.models.StateStudyCards

class StudyCardsViewModel(
    private val deckId: Long,
    private val studyCardsRepository: StudyCardsRepository
) :
    ViewModel(),
    MVI<StateStudyCards, IntentStudyCards, EventStudyCards> by mvi(
        StateStudyCards(
            isLoading = true,
            allCuntCards = MAX_COUNT_CARDS_FOR_STUDY
        )
    ) {

    private val frontRichTextState: RichTextState = RichTextState()
    private val backRichTextState: RichTextState = RichTextState()

    private val cards: MutableList<Card> = mutableListOf()
    private var currentCardPosition: Int = 0

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            val cardsFromDb = studyCardsRepository.getCardsForStudyFromDeck(
                deckId = deckId,
                countCards = MAX_COUNT_CARDS_FOR_STUDY
            )

            this@StudyCardsViewModel.cards.addAll(cardsFromDb)

            frontRichTextState.setHtml(cards[currentCardPosition].front)
            val front = frontRichTextState.annotatedString

            backRichTextState.setHtml(cards[currentCardPosition].back)
            val back = backRichTextState.annotatedString

            updateState {
                copy(
                    isLoading = false,
                    currentNumberCard = currentCardPosition + 1,
                    front = front,
                    back = back
                )
            }
        }
    }

    override fun onIntent(intent: IntentStudyCards) {
        when (intent) {
            IntentStudyCards.OnEditCurrentCardClick -> openCardEditor()
            IntentStudyCards.NexCard -> nextStep()
        }
    }

    private fun nextStep() {
        viewModelScope.launch {
            if (lastState.showCardBack) {
                currentCardPosition++
                if (cards.count() < currentCardPosition) {
                    emitEvent(
                        EventStudyCards.OpenFinishScreen
                    )
                } else {
                    frontRichTextState.setHtml(cards[currentCardPosition].front)
                    val front = frontRichTextState.annotatedString

                    backRichTextState.setHtml(cards[currentCardPosition].back)
                    val back = backRichTextState.annotatedString

                    updateState {
                        copy(
                            showCardBack = false,
                            currentNumberCard = currentCardPosition + 1,
                            front = front,
                            back = back
                        )
                    }
                }
            } else {
                updateState {
                    copy(
                        showCardBack = true
                    )
                }
            }
        }
    }

    private fun openCardEditor() {
        viewModelScope.launch {
            emitEvent(
                EventStudyCards.OpenCardEditor(cards[currentCardPosition].id)
            )
        }
    }

    companion object {
        const val MAX_COUNT_CARDS_FOR_STUDY = 50
    }
}
