package com.altamirano.proyectofinal.ui.viewmodels.login

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altamirano.proyectofinal.logic.login.GetUserWithNameAndPassword
import com.altamirano.proyectofinal.ui.core.UIStates
import com.google.firebase.auth.FirebaseAuth


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragmentVM:ViewModel() {

    private var auth: FirebaseAuth?= null

    private val context: FragmentActivity?= null

    var uiState = MutableLiveData<UIStates>()
    var idUser =MutableLiveData<Int>()


    fun getUserFromDB(name:String, password:String, contex: Context) {
        viewModelScope.launch {
            uiState.postValue(UIStates.LoadingState(true))

            GetUserWithNameAndPassword(contex).invoke(name, password)
                .collect {
                    it.onSuccess {
                        idUser.postValue(it.id)
                    }
                    it.onFailure {
                        uiState.postValue(UIStates.ErrorState(it.message.toString()))
                    }

                }
            delay(3000)
            uiState.postValue(UIStates.LoadingState(false))
        }



}

    fun authWhitFireBase(email:String, password:String,auth:FirebaseAuth, context:FragmentActivity) {
        viewModelScope.launch {
            uiState.postValue(UIStates.LoadingState(false))
            auth.signInWithEmailAndPassword(email,password).
            addOnCompleteListener(context){task->
                if(task.isSuccessful){
                    uiState.postValue(UIStates.SuccessState(true))
                }else{
                    Log.w("TAG","Error al entrar con correo", task.exception)
                    uiState.postValue(UIStates.ErrorState(task.exception?.message.toString()))
                }
            }
            delay(500)
            uiState.postValue(UIStates.LoadingState(false))
        }
    }
}