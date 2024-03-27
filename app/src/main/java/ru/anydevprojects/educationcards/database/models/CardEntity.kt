package ru.anydevprojects.educationcards.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "deck_id") val deckId: String,
    val front: String,
    val back: String
)
