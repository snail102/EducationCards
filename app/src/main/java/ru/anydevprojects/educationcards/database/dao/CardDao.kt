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

    @Query("SELECT * FROM cardentity WHERE id IN (:cardId)")
    suspend fun getCardById(cardId: Long): CardEntity

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    suspend fun getAllCardByDeckId(deckId: Long): List<CardEntity>

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId) LIMIT :countCards")
    suspend fun getSomeCardsByDeckId(deckId: Long, countCards: Int): List<CardEntity>

    @Query("SELECT * FROM cardentity WHERE deck_id IN (:deckId)")
    fun getAllCardByDeckIdFlow(deckId: Long): Flow<List<CardEntity>>

    @Query("UPDATE cardentity SET front = :front, back = :back WHERE id IN (:cardId)")
    suspend fun updateById(cardId: Long, front: String, back: String)

    @Query("DELETE FROM cardentity WHERE id IN (:cardId)")
    suspend fun deleteById(cardId: Long)

    @Delete
    suspend fun delete(cardEntity: CardEntity)
}
