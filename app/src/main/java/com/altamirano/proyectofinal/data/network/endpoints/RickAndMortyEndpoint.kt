package com.altamirano.proyectofinal.data.network.endpoints


import com.altamirano.proyectofinal.data.network.entities.ram.RamEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyEndpoint {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ) : Response<RamEntity>


}
