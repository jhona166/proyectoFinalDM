package com.altamirano.proyectofinal.data.network.entities.marvel.characters

data class MarvelApiChars(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)