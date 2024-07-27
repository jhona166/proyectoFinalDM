package com.altamirano.proyectofinal.logic.login

import com.altamirano.proyectofinal.data.firebase.FirestoreRepository
import com.altamirano.proyectofinal.ui.entities.users.UserLogin



import kotlinx.coroutines.flow.flow

class SaveUserFireStoreUserCase {

    suspend fun invoke(user: UserLogin) = flow {
        val x = FirestoreRepository().saveUserLogin(user)

        x.onSuccess {
            emit(it)
        }

        x.onFailure {
            emit(false)
        }
    }

}