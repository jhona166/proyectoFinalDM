package com.altamirano.proyectofinal.ui.viewmodels.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altamirano.proyectofinal.logic.login.CreateUserWithNameAndPassword
import com.altamirano.proyectofinal.ui.core.UIStates

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterFragmentVM: ViewModel() {

    var uiState = MutableLiveData<UIStates>()

    fun saveUser(name: String, password: String, context: Context) {
        viewModelScope.launch {
            uiState.postValue(UIStates.LoadingState(true))
            CreateUserWithNameAndPassword(context).invoke(name, password)
                .collect {
                    it.onSuccess {
                        uiState.postValue(UIStates.SuccessState(it))
                    }
                    it.onFailure {
                        uiState.postValue(UIStates.ErrorState(it.message.toString()))
                    }
                }
            delay(500)
            uiState.postValue(UIStates.LoadingState(false))
        }
    }
}