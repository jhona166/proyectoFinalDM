package com.altamirano.proyectofinal.data.network.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.altamirano.proyectofinal.data.network.dao.marvel.MarvelCharsDAO
import com.altamirano.proyectofinal.data.network.dao.marvel.RamCharsDAO
import com.altamirano.proyectofinal.data.network.entities.marvel.characters.database.MarvelCharsBD
import com.altamirano.proyectofinal.data.network.entities.ram.database.RamCharsDB


@Database(
    entities = [MarvelCharsBD::class,RamCharsDB::class],
    version = 1
)
abstract class MarvelConnectionDB : RoomDatabase() {

    abstract fun marvelDao(): MarvelCharsDAO

    abstract fun ramDao(): RamCharsDAO
}