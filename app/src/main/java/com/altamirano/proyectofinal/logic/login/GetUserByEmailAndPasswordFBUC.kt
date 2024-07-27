package com.altamirano.proyectofinal.logic.login

import com.altamirano.proyectofinal.data.firebase.FirestoreRepository
import com.altamirano.proyectofinal.ui.entities.users.UserLogin


import kotlinx.coroutines.flow.flow

class GetUserByEmailAndPasswordFBUC {

    suspend fun invoke(id: String) = flow<Result<UserLogin>> {
        FirestoreRepository().getUserById(id)
            .onSuccess {
                emit(Result.success(it.first()))
            }
            .onFailure { error ->
                emit(Result.failure(error))
            }
    }
}