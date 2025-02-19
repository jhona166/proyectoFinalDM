package com.altamirano.proyectofinal.data.network.dao.marvel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RamCharsDAO {
    @Query("select * from RamCharsDB")
    fun getAllCharacters(): List<RamCharsDB>

    @Insert
    fun insertRamChar(ch: List<RamCharsDB>)
}