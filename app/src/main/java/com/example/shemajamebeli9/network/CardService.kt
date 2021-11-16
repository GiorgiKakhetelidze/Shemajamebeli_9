package com.example.shemajamebeli9.network

import com.example.shemajamebeli9.model.CardItem
import retrofit2.Response
import retrofit2.http.GET

interface CardService {

    @GET("05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getCards() : Response<List<CardItem>>
}