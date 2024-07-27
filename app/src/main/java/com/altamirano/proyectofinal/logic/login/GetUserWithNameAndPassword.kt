
package com.altamirano.proyectofinal.logic.login
import android.content.Context
import com.altamirano.proyectofinal.data.local.repositories.DataBaseRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn



class GetUserWithNameAndPassword(
    private val context: Context
) {

    fun invoke(
        name: String, password: String
    ) = flow {
        delay(2000)
        val us = DataBaseRepository
            .getDBConnection(context)
            .getUserDao()
            .getUserByPassAndUser(name, password)
        if (us != null) {
            emit(
                Result.success(us)
            )
        } else {
            emit(
                Result.failure(
                    Exception(
                        "No hay coincidencia con esos valores. " +
                                "Por favor revise los valores ingresados y vuelva a intentarlo"
                    )
                )
            )
        }
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}
