package ru.anydevprojects.educationcards.importDeck.data.models

data class DataFromDb(
    val name: String,
    val path: String,
    val cardList: List<CardFromDb>
)
