/*package com.torres.finalproject.ui.viewmodels.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.torres.finalproject.logic.marvel.GetAllMarvelCharsUserCase
import com.torres.finalproject.ui.core.UIStates
import com.torres.finalproject.ui.entities.marvel.MarvelCharsUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListMarvelCharsVM : ViewModel() {

    val itemsMarvel = MutableLiveData<List<MarvelCharsUI>>()
    val uiState = MutableLiveData<UIStates>()

    fun initData() {
        Log.d("TAG", "Ingresando al VM")
        viewModelScope.launch {
            uiState.postValue(UIStates.LoadingState(true))
            GetAllMarvelCharsUserCase().invoke().collect { respUC ->
                respUC.onSuccess { items ->
                    itemsMarvel.postValue(items)
                }
                respUC.onFailure {
                    uiState.postValue(UIStates.ErrorState(it.message.toString()))
                    Log.d("TAG", it.message.toString())
                }
            }
            delay(500)
            uiState.postValue(UIStates.LoadingState(false))
        }
    }
}

 */