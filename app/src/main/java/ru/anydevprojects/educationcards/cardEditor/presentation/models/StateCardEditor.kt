package ru.anydevprojects.educationcards.cardEditor.presentation.models

import com.mohamedrejeb.richeditor.model.RichTextState

data class StateCardEditor(
    val isLoading: Boolean = false,
    val frontStateRichText: RichTextState = RichTextState(),
    val backStateRichText: RichTextState = RichTextState()
)
