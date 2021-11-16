package com.example.shemajamebeli9.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "card_items")
@JsonClass(generateAdapter = true)
data class CardItem(
    @Json(name = "cover")
    val cover: String,
    @Json(name = "liked")
    val liked: Boolean,
    @Json(name = "price")
    val price: String,
    @Json(name = "title")
    val title: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}