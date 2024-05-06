package ru.anydevprojects.educationcards.studyCards.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.anydevprojects.educationcards.R
import ru.anydevprojects.educationcards.studyCards.presentation.models.IntentStudyCards
import ru.anydevprojects.educationcards.utils.pixelsToDp

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

    // / val painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_tap))

    // val bitmap = ImageBitmap.imageResource(id = R.drawable.ic_tap)

    var columnSize by remember { mutableStateOf(IntSize.Zero) }

    val textMeasurer = rememberTextMeasurer()

    val textToDraw = "Нажмите для отображения ответа"

    val style = TextStyle(
        fontSize = 24.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )

    val textLayoutResult = remember(textToDraw) {
        textMeasurer.measure(textToDraw, style)
    }

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .onSizeChanged { newSize ->
                    columnSize = newSize
                }
                .verticalScroll(rememberScrollState())
        ) {
            val defaultCardModifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = pixelsToDp(pixels = columnSize.height))
                .padding(horizontal = 16.dp, vertical = 32.dp)
            Card(
                modifier = if (true) {
                    defaultCardModifier
                } else {
                    defaultCardModifier.drawWithContent {
                        drawContent()
                        drawText(
                            textLayoutResult = textMeasurer.measure(
                                textToDraw,
                                style,
                                constraints = Constraints(
                                    maxHeight = size.height.toInt(),
                                    maxWidth = size.width.toInt()
                                )
                            ),
                            topLeft = Offset(
                                16.dp.toPx(),
                                size.height / 2
                            )
                        )
                    }
                },
                onClick = {
                    viewModel.onIntent(IntentStudyCards.NexCard)
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.front,
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                    if (state.showCardBack) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = state.back,
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                    } else {
                        HelpMessage(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HelpMessage(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.ic_tap),
            contentDescription = "tap for show answer"
        )
        Text(
            text = "Нажмите для отображения ответа",
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Gray, fontSize = 14.sp)
        )
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
