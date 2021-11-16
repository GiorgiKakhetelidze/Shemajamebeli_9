package com.example.shemajamebeli9.app

import android.app.Application
import android.content.Context
import com.example.shemajamebeli9.data.DataStore

class CardApplication : Application(){

    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        DataStore.initDataBase(this)
    }


}