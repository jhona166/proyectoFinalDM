package com.altamirano.proyectofinal.data.firebase

import android.util.Log
import com.altamirano.proyectofinal.ui.entities.users.UserLogin
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

import kotlinx.coroutines.tasks.await

class FirestoreRepository {

    private var db: FirebaseFirestore = Firebase.firestore

    suspend fun saveUserLogin(user: UserLogin) =kotlin.runCatching {
        val id = db.collection("users")
            .add(user)
            .await()
            .id
        return@runCatching true
    }

    suspend fun getUserById(id: String) = kotlin.runCatching {
        var items = arrayListOf<UserLogin>()
        db.collection("users")
            .whereEqualTo("uuid", id)
            .get()
            .await()
            .forEach {
                items.add(it.toObject<UserLogin>())
            }
        return@runCatching items
    }

}