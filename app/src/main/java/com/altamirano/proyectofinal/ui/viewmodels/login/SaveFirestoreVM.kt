package com.altamirano.proyectofinal.ui.viewmodels.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altamirano.proyectofinal.logic.login.GetUserByEmailAndPasswordFBUC
import com.altamirano.proyectofinal.logic.login.SaveUserFireStoreUserCase
import com.altamirano.proyectofinal.ui.core.UIStates
import com.altamirano.proyectofinal.ui.entities.users.UserLogin

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SaveFirestoreVM : ViewModel() {
    val userUI get() = _userUI
    private var _userUI = MutableLiveData<UIStates>()

    val userLogin get() = _userLogin
    private var _userLogin = MutableLiveData<UserLogin>()

    fun saveUserFirestore(user: UserLogin) {
        viewModelScope.launch {
            SaveUserFireStoreUserCase()
                .invoke(user)
                .collect {
                    if (it) {
                        _userUI.postValue(UIStates.SuccessState(true))
                    } else {
                        _userUI.postValue(UIStates.ErrorState("Ocurrio un error en la peticion. Intentelo mas tarde"))
                    }
                }
        }
    }

    fun getUserByIdFireStore(id: String) {
        viewModelScope.launch {
            _userUI.postValue(UIStates.LoadingState(true))
            GetUserByEmailAndPasswordFBUC().invoke(id)
                .collect { resp ->
                    resp
                        .onSuccess { user ->
                            _userLogin.postValue(user)
                        }
                        .onFailure {
                            _userUI.postValue(UIStates.ErrorState(it.message.toString()))
                        }

                }
            delay(500)
            _userUI.postValue(UIStates.LoadingState(false))
        }
    }
}