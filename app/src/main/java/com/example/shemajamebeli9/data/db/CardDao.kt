package com.example.shemajamebeli9.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shemajamebeli9.model.CardItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM card_items")
    fun getCards(): Flow<List<CardItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(card: List<CardItem>)
}