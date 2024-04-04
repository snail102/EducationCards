package ru.anydevprojects.educationcards.cardEditor.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.ui.material3.OutlinedRichTextEditor
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.educationcards.cardEditor.presentation.models.EventCardEditor
import ru.anydevprojects.educationcards.cardEditor.presentation.models.IntentCardEditor
import ru.anydevprojects.educationcards.core.mvi.CollectEvent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CardEditorScreen(
    cardId: String,
    viewModel: CardEditorViewModel = koinViewModel(
        parameters = {
            parametersOf(cardId)
        }
    ),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    CollectEvent(viewModel.event) { event ->
        when (event) {
            EventCardEditor.CloseScreen -> {
                navController.popBackStack()
            }
        }
    }

    Scaffold(
        topBar = {
            EditCardAppBar(
                onSaveClickBtn = {
                    viewModel.onIntent(IntentCardEditor.OnSaveCardClick)
                }
            )
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValue)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 60.dp)
            ) {
                OutlinedRichTextEditor(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.frontStateRichText,
                    interactionSource = interactionSource,
                    label = {
                        Text(text = "Вопрос")
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedRichTextEditor(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.backStateRichText,
                    label = {
                        Text(text = "Ответ")
                    }
                )
            }

            if (WindowInsets.isImeVisible) {
                RichTextStyleRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding()
                        .padding(horizontal = 16.dp)
                        .background(color = Color.Cyan, shape = RoundedCornerShape(16.dp))
                        .align(Alignment.BottomCenter),
                    state = if (isFocused) state.frontStateRichText else state.backStateRichText
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditCardAppBar(onSaveClickBtn: () -> Unit) {
    TopAppBar(title = { Text("Редактирование карты") }, actions = {
        IconButton(onClick = onSaveClickBtn) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
        }
    })
}
