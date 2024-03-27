package ru.anydevprojects.educationcards.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.anydevprojects.educationcards.database.models.CardEntity

@Dao
interface CardDao {

    @Insert
    fun insertAll(cards: List<CardEntity>)

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    fun getAllCardByDeckId(deckId: String): List<CardEntity>

    @Delete
    fun delete(cardEntity: CardEntity)
}
