package ru.anydevprojects.educationcards.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.anydevprojects.educationcards.database.models.CardEntity

@Dao
interface CardDao {

    @Insert
    suspend fun insertAll(cards: List<CardEntity>)

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    suspend fun getAllCardByDeckId(deckId: String): List<CardEntity>

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    fun getAllCardByDeckIdFlow(deckId: String): Flow<List<CardEntity>>

    @Delete
    suspend fun delete(cardEntity: CardEntity)
}
