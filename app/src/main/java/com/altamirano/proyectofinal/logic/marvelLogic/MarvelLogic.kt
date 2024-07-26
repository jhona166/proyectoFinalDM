package com.altamirano.proyectofinal.logic.marvelLogic

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.altamirano.proyectofinal.data.network.endpoints.MarvelEndpoint
import com.altamirano.proyectofinal.data.network.entities.marvel.characters.database.MarvelCharsBD
import com.altamirano.proyectofinal.data.network.entities.marvel.characters.getMarvelChars
import com.altamirano.proyectofinal.data.network.repositories.ApiConnection
import com.altamirano.proyectofinal.logic.data.MarvelChars
import com.altamirano.proyectofinal.logic.data.getMarvelCharsDB
import com.altamirano.proyectofinal.ui.utilities.ProyectoFinal





import java.lang.Exception
import java.lang.RuntimeException

class MarvelLogic {

    suspend fun getMarvelChars(name: String, limit: Int): ArrayList<MarvelChars> {

        var itemList = arrayListOf<MarvelChars>()


        val response =
            ApiConnection.getService(ApiConnection.typeApi.Marvel, MarvelEndpoint::class.java)
                .getCharacterStartWith(name, limit)

        if (response.isSuccessful) {
            response.body()!!.data.results.forEach {

                val m = it.getMarvelChars()
                itemList.add(m)
            }
        }

        return itemList
    }

    suspend fun getAllMarvelChars(offset: Int, limit: Int): ArrayList<MarvelChars> {

        var itemList = arrayListOf<MarvelChars>()


        val response =
            ApiConnection.getService(ApiConnection.typeApi.Marvel, MarvelEndpoint::class.java)
                .getAllMarvelChars(offset, limit)

        if (response.isSuccessful) {
            response.body()!!.data.results.forEach {
                val m = it.getMarvelChars()
                itemList.add(m)
            }
        }

        return itemList
    }

    suspend fun getAllMarvelChardDB(): List<MarvelChars> {
        val items: ArrayList<MarvelChars> = arrayListOf()
        val items_aux = ProyectoFinal.getDbInstance().marvelDao().getAllCharacters()
        items_aux.forEach {
            items.add(
                MarvelChars(
                    it.id, it.name, it.comic, it.image
                )
            )
        }
        return items
    }

    suspend fun getInitChars(limit: Int, offset: Int): MutableList<MarvelChars> {

        var items = mutableListOf<MarvelChars>()
        try {

            items = MarvelLogic().getAllMarvelChardDB().toMutableList()


            if (items.isEmpty()) {
                items = MarvelLogic().getAllMarvelChars(offset, limit)
                MarvelLogic().insertMarvelCharstoDB(items)
            }

        } catch (ex: Exception) {
            throw RuntimeException(ex.message)

        }finally {
            return items
        }





    }

    suspend fun insertMarvelCharstoDB(items: List<MarvelChars>){

        var itemsDB = arrayListOf<MarvelCharsBD>()


        items.forEach {
                itemsDB.add(it.getMarvelCharsDB())
        }

        ProyectoFinal.getDbInstance().marvelDao().insertMarvelChar(itemsDB)

    }
}