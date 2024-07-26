package com.altamirano.proyectofinal.data.network.entities.ram

import com.altamirano.proyectofinal.logic.data.RamChars

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)


fun Result.getRamChars(): RamChars {


    val a = RamChars(
        id,name,status,species,location.name,origin.name,image,gender)


    return a
}