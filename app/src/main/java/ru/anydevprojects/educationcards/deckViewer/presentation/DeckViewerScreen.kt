package ru.anydevprojects.educationcards.deckViewer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.educationcards.deckViewer.presentation.models.StateDeckViewer

@Composable
fun DeckViewerScreen(
    deckId: String,
    viewModel: DeckViewerViewModel = koinViewModel(
        parameters = {
            parametersOf(deckId)
        }
    )
) {
    val state = viewModel.state.collectAsState()

    // TODO передавать имя колоды, чтобы при загрузке уже было отражено имя и крутился прогрес бар
    if (state.value.isLoading) {
        ProgressLoading()
    } else {
        DeckViewerContent(
            stateContent = state
        )
    }
}

@Composable
private fun ProgressLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DeckViewerContent(stateContent: State<StateDeckViewer>) {
    val state by stateContent
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = state.name)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = state.cards,
                key = { card ->
                    card.front
                }
            ) { card ->
                CardInDeck(
                    front = card.front
                )
            }
        }
    }
}

@Composable
private fun CardInDeck(front: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = front)
    }
}
