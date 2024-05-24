package ru.anydevprojects.educationcards.profile.presentation.models

data class StateProfile(
    val isLoading: Boolean = true,
    val isAvailableDeckStatistics: Boolean = false,
    val isAvailableCardsStatistics: Boolean = false
)
