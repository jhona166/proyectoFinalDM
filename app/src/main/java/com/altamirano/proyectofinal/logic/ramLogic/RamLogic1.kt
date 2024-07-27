package com.altamirano.proyectofinal.logic.ramLogic

import com.altamirano.proyectofinal.data.network.endpoints.RickAndMortyEndpoint
import com.altamirano.proyectofinal.data.network.entities.ram.database.RamCharsDB
import com.altamirano.proyectofinal.data.network.entities.ram.getRamChars
import com.altamirano.proyectofinal.data.network.repositories.ApiConnection
import com.altamirano.proyectofinal.logic.data.RamChars
import com.altamirano.proyectofinal.logic.data.getRamCharsDB
import com.altamirano.proyectofinal.ui.utilities.ProyectoFinal




class RamLogic1 {

    suspend fun getAllCharactersRam(): List<RamChars> {
        val itemList = mutableListOf<RamChars>()

        //Consumo del EndPoint
        for (page in 4..8) {
            val response = ApiConnection.getService(
                ApiConnection.typeApi.RickAndMorty,
                RickAndMortyEndpoint::class.java
            ).getAllCharacters(page)
            if (response.isSuccessful) {
                response.body()?.results?.forEach {
                    val me = it.getRamChars()
                    itemList.add(me)
                }
            } else {
                // Manejo de errores según sea necesario
                break
            }
        }

        return itemList











    }

    suspend fun getAllRamCharsDB(): List<RamChars> {
        val items: ArrayList<RamChars> = arrayListOf()
        val items_aux = ProyectoFinal.getDbInstance().ramDao().getAllCharacters()
        items_aux.forEach {
            items.add(
                RamChars(
                    it.id,
                    it.nombre,
                    it.estado,
                    it.especie,
                    it.ubicacion,
                    it.origen,
                    it.imagen,
                    it.episode
                )
            )
        }
        return items
    }

    suspend fun insertRamCharstoDB(items: List<RamChars>){

        var itemsDB = arrayListOf<RamCharsDB>()


        items.forEach {
            itemsDB.add(it.getRamCharsDB())
        }

        ProyectoFinal.getDbInstance().ramDao().insertRamChar(itemsDB)

    }


}