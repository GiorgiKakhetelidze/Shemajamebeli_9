package com.example.shemajamebeli9.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shemajamebeli9.model.CardItem

@Database(entities = [CardItem::class], version = 1)
abstract class CardDb : RoomDatabase() {
    abstract fun getCharacterDao(): CardDao
}