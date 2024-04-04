package ru.anydevprojects.educationcards.studyCards.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.educationcards.studyCards.presentation.models.IntentStudyCards

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
    Scaffold(
        topBar = {
            StudyCardsTopAppBar(
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StudyCardsTopAppBar(onCloseBtnClick: () -> Unit, onEditCardClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "0/50")
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
