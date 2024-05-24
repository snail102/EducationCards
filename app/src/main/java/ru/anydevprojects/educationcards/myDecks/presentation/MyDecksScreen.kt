package ru.anydevprojects.educationcards.myDecks.presentation

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.anydevprojects.educationcards.core.mvi.CollectEvent
import ru.anydevprojects.educationcards.core.navigation.Screens
import ru.anydevprojects.educationcards.myDecks.presentation.models.EventMyDecks
import ru.anydevprojects.educationcards.myDecks.presentation.models.IntentMyDecks
import ru.anydevprojects.educationcards.myDecks.presentation.models.StateMyDecks

@Composable
fun MyDecksScreen(viewModel: MyDecksViewModel = koinViewModel(), navController: NavController) {
    val state = viewModel.state.collectAsState()

    val pickFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { fileUri ->
        if (fileUri != null) {
            Log.d("filePicker", "fileUri: $fileUri")
            viewModel.onIntent(IntentMyDecks.SelectFile(fileUri))
        } else {
            Log.d("filePicker", "fileUri is null")
        }
    }

    CollectEvent(viewModel.event) { event ->
        when (event) {
            EventMyDecks.ShowImportNewDeck -> {
                pickFileLauncher.launch("*/*")
            }

            is EventMyDecks.OpenDeck -> {
                navController.navigate(
                    Screens.DeckViewer.getRouteWithArgs(
                        deckId = event.deckId,
                        deckName = event.deckName
                    )
                )
            }

            EventMyDecks.ShowErrorGetSelectedFile -> {
            }

            EventMyDecks.ShowErrorIncorrectFileExtension -> {
            }
        }
    }

    MyDecksContent(
        state = state,
        onDeckClick = { id, name ->
            viewModel.onIntent(IntentMyDecks.OnDeckClick(id = id, name = name))
        },
        onImportNewDeckClick = {
            viewModel.onIntent(IntentMyDecks.OnImportNewDeckClick)
        }
    )
}

@Composable
private fun MyDecksContent(
    state: State<StateMyDecks>,
    onDeckClick: (Long, String) -> Unit,
    onImportNewDeckClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBarMyDecks(
                onImportNewDeckClick = onImportNewDeckClick
            )
        }
    ) {
        if (state.value.isLoading) {
            ProgressLoading()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = state.value.myDecks,
                    key = { deck ->
                        deck.id
                    }
                ) { deck ->
                    CardMyDeck(
                        name = deck.name,
                        onClick = {
                            onDeckClick(deck.id, deck.name)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBarMyDecks(onImportNewDeckClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Мои колоды") },
        actions = {
            IconButton(onClick = onImportNewDeckClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun CardMyDeck(name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = name, maxLines = 2, minLines = 1, textAlign = TextAlign.Center)
        }
    }
}
