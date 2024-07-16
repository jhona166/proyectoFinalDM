package com.altamirano.proyectofinal.data.network.endpoints


import com.altamirano.proyectofinal.data.network.entities.ram.RamEntity
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyEndpoint {
    @GET("character")
    suspend fun getAllCharacters( ) : Response<RamEntity>


}
