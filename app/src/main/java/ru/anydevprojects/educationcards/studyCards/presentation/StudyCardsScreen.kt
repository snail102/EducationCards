package ru.anydevprojects.educationcards.studyCards.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.educationcards.studyCards.presentation.models.IntentStudyCards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyCardsScreen(
    deckId: String,
    navController: NavController,
    viewModel: StudyCardsViewModel = koinViewModel(
        parameters = {
            parametersOf(deckId)
        }
    )
) {
    val state by viewModel.state.collectAsState()

    val frontRich = rememberRichTextState()
    val backRich = rememberRichTextState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            StudyCardsTopAppBar(
                currentNumberCard = state.currentNumberCard,
                onCloseBtnClick = {
                    navController.popBackStack()
                },
                onEditCardClick = {
                    viewModel.onIntent(IntentStudyCards.OnEditCurrentCardClick)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                onClick = {
                    viewModel.onIntent(IntentStudyCards.NexCard)
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                ) {
                    RichText(
                        modifier = Modifier.fillMaxWidth(),
                        state = frontRich.apply {
                            setHtml(state.front)
                        }
                    )
                    if (state.showCardBack) {
                        RichText(
                            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                            state = backRich.apply {
                                setHtml(state.back)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StudyCardsTopAppBar(
    currentNumberCard: Int,
    onCloseBtnClick: () -> Unit,
    onEditCardClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "$currentNumberCard/50")
        },
        navigationIcon = {
            IconButton(onClick = onCloseBtnClick) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onEditCardClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
        }

    )
}
