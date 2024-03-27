package ru.anydevprojects.educationcards.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.anydevprojects.educationcards.database.dao.CardDao
import ru.anydevprojects.educationcards.database.dao.DeckDao
import ru.anydevprojects.educationcards.database.models.CardEntity
import ru.anydevprojects.educationcards.database.models.DeckEntity

@Database(entities = [DeckEntity::class, CardEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
}
