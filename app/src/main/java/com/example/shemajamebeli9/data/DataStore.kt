package com.example.shemajamebeli9.data

import android.content.Context
import androidx.room.Room
import com.example.shemajamebeli9.data.db.CardDb
import java.lang.RuntimeException
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.shemajamebeli9.app.CardApplication.Companion.appContext


object DataStore {

    private var dataBase: CardDb? = null

    val db get() = dataBase ?: throw RuntimeException("not initialized!!")

    fun initDataBase(context: Context){
        dataBase = Room.databaseBuilder(context, CardDb::class.java, "card_items").build()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable() =
        (appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
}