package com.example.shemajamebeli9.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {
    val cardService by lazy { createCardService() }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createCardService(): CardService {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl("https://run.mocky.io/v3/")
        retrofitBuilder.client(
            OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()
        )
        retrofitBuilder.addConverterFactory(moshConverter())
        return retrofitBuilder.build().create(CardService::class.java)
    }


    private fun moshConverter() =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )
}