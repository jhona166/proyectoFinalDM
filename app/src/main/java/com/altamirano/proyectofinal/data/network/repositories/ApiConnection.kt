package com.altamirano.proyectofinal.data.network.repositories

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConnection {

    enum class typeApi {
                       Marvel, RickAndMorty
    }


    private val API_MARVEL = "https://gateway.marvel.com/v1/public/"

    private val API_RAM= "https://rickandmortyapi.com/api/"

    private fun getConnnection(base: String): Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    suspend fun <T, E : Enum<E>> getService(api: E, service: Class<T>): T {
        var BASE = ""
        when (api.name) {

            typeApi.Marvel.name -> {
                BASE = API_MARVEL


            }

            typeApi.RickAndMorty.name -> {
                BASE = API_RAM
            }


        }
        return getConnnection(BASE).create(service)
    }
}