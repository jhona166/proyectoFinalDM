package com.altamirano.proyectofinal.data.network.entities.marvel.characters

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)