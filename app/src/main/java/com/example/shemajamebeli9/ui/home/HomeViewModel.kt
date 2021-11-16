package com.example.shemajamebeli9.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.shemajamebeli9.data.DataStore
import com.example.shemajamebeli9.model.CardItem
import com.example.shemajamebeli9.network.NetworkClient
import com.example.shemajamebeli9.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.lang.Exception

@RequiresApi(Build.VERSION_CODES.M)
class HomeViewModel : ViewModel() {

    private val _cards = MutableLiveData<Resource<List<CardItem>>>()
    val cards: LiveData<Resource<List<CardItem>>> get() = _cards


    init {
        if (DataStore.isNetworkAvailable())
            getDataFromServer()
        else
            getDataFromRoom()
    }

    private fun getDataFromRoom() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cards = DataStore.db.getCharacterDao().getCards()
                cards.collect {
                    if (it.isNotEmpty())
                        _cards.postValue(Resource.Success(it))
                    else
                        _cards.postValue(Resource.Error(message = "Room is empty"))
                }
            } catch (e: Exception) {
                _cards.postValue(Resource.Error(message = e.message.toString()))
            }
        }
    }

    private fun getDataFromServer() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = NetworkClient.cardService.getCards()
                val cards = response.body()
                if (response.isSuccessful && cards != null) {
                    _cards.postValue(Resource.Success(data = cards))
                    DataStore.db.getCharacterDao().insertCards(card = cards)
                } else
                    _cards.postValue(Resource.Error(message = response.message()))
            }
        } catch (e: Exception) {
            _cards.postValue(Resource.Error(message = e.message.toString()))
        }

    }

}