package ru.anydevprojects.educationcards.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeckEntity(
    @PrimaryKey val uid: String,
    val name: String,
    val path: String
)
