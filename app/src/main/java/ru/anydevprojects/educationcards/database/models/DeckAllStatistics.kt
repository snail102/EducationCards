package ru.anydevprojects.educationcards.database.models

data class DeckAllStatistics(
    val id: String,
    val name: String,
    val numberOfStudies: Int,
    val lastStudyTimestamp: Long
)
