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

    @Query("SELECT * FROM cardentity WHERE uid IN (:cardId)")
    suspend fun getCardById(cardId: String): CardEntity

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    suspend fun getAllCardByDeckId(deckId: String): List<CardEntity>

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId) LIMIT :countCards")
    suspend fun getSomeCardsByDeckId(deckId: String, countCards: Int): List<CardEntity>

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    fun getAllCardByDeckIdFlow(deckId: String): Flow<List<CardEntity>>

    @Query("UPDATE cardentity SET front = :front, back = :back WHERE uid IN (:cardId)")
    suspend fun updateById(cardId: String, front: String, back: String)

    @Query("DELETE FROM cardentity WHERE uid IN (:cardId)")
    suspend fun deleteById(cardId: String)

    @Delete
    suspend fun delete(cardEntity: CardEntity)
}
