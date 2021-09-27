package com.example.examenmoviles.dto

data class FirestoreTorneo(
    var nombreTorneo: String = "",
    var jugadoresTorneo: Int = 0,
    var lugarTorneo: String = "",
    var descripcionTorneo: String = ""
) {
}