package com.example.plataformasaludvirtual.personas

data class Persona(
    val id: String = "",
    val identificacion: String = "",
    val primerNombre: String = "",
    val segundoNombre: String = "",
    val apellido: String = "",
    val sexo: String = "",
    val edad: Int = 0,
    val telefono: String = "",
    val correo: String = "",
    val direccion: String = ""
) {
    val nombreCompleto: String
        get() = if (segundoNombre.isNotBlank()) {
            "$primerNombre $segundoNombre $apellido"
        } else {
            "$primerNombre $apellido"
        }

}