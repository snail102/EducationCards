package ru.anydevprojects.educationcards.cardEditor.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CardEditorScreen(
    cardId: String,
    viewModel: CardEditorViewModel = koinViewModel(
        parameters = {
            parametersOf(cardId)
        }
    )
) {
    Scaffold {
    }
}
