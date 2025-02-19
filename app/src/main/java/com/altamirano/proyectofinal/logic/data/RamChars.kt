package com.altamirano.proyectofinal.logic.data

import android.os.Parcelable
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD
import com.example.dispositivosmoviles.data.entities.ram.database.RamCharsDB
import kotlinx.parcelize.Parcelize

@Parcelize
data class RamChars(
    val id:Int,
    val nombre: String,
    val estado: String,
    val especie: String,
    val ubicacion: String,
    val origen: String,
    val imagen: String,
    val episode: String
) : Parcelable

fun RamChars.getRamCharsDB(): RamCharsDB {
    return RamCharsDB(
        id,
        nombre,
        estado,
        especie,
        ubicacion,
        origen,
        imagen,
        episode
    )
}