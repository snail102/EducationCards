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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.educationcards.core.navigation.Screens
import ru.anydevprojects.educationcards.deckViewer.presentation.models.StateDeckViewer
import ru.anydevprojects.educationcards.domain.models.Card

@Composable
fun DeckViewerScreen(
    deckId: Long,
    deckName: String,
    navController: NavController,
    viewModel: DeckViewerViewModel = koinViewModel(
        parameters = {
            parametersOf(deckId, deckName)
        }
    )
) {
    val state = viewModel.state.collectAsState()

    DeckViewerContent(
        stateContent = state,
        onCardClick = { card: Card ->
            navController.navigate(Screens.CardEditor.getRouteWithArgs(cardId = card.id))
        },
        onStartStudyClick = {
            navController.navigate(Screens.StudyCards.getRouteWithArgs(deckId))
        }
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeckViewerContent(
    stateContent: State<StateDeckViewer>,
    onCardClick: (Card) -> Unit,
    onStartStudyClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    val state by stateContent
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DeckAppBar(
                title = state.name,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            ProgressLoading()
        } else {
            Column(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .fillMaxSize()
            ) {
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onStartStudyClick
                ) {
                    Text(text = "Start")
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = state.cards,
                        key = { card ->
                            card.front
                        }
                    ) { card ->
                        CardInDeck(
                            card = card,
                            onClick = onCardClick
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeckAppBar(title: String, scrollBehavior: TopAppBarScrollBehavior) {
    LargeTopAppBar(title = { Text(text = title) }, scrollBehavior = scrollBehavior)
}

@Composable
private fun CardInDeck(card: Card, onClick: (Card) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick(card)
        }
    ) {
        Text(text = card.front, modifier = Modifier.padding(16.dp))
    }
}
