package com.altamirano.proyectofinal.ui.viewmodels.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altamirano.proyectofinal.logic.login.GetUserWithNameAndPassword
import com.altamirano.proyectofinal.ui.core.UIStates


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragmentVM : ViewModel() {

    var uiState = MutableLiveData<UIStates>()
    var idUser = MutableLiveData<Int>()

    fun getUserFromDB(name: String, password: String, context: Context) {

        viewModelScope.launch {
            uiState.postValue(UIStates.LoadingState(true))

            GetUserWithNameAndPassword(context).invoke(name, password).collect {
                it.onSuccess {
                    idUser.postValue(it.id)
                }
                it.onFailure {
                    uiState.postValue(
                        UIStates.ErrorState(it.message.toString())
                    )
                }
            }

            delay(500)
            uiState.postValue(UIStates.LoadingState(false))

        }
    }
}