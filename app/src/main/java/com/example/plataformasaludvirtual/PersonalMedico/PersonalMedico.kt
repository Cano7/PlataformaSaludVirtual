package com.example.plataformasaludvirtual.PersonalMedico

data class PersonalMedico(
    val identificacion: String = "",
    val codigoMedico: String = "",
    val primerNombre: String = "",
    val segundoNombre: String = "",
    val apellido: String = "",
    val edad: Int = 0,
    val correo: String = "",
    val direccion: String = "",
    val sexo: String = "",
    val active: Boolean = true,
    val nombreCargo: String = "",
    val nombreDependencia: String = ""
) {
    val nombreCompleto: String
        get() = if (segundoNombre.isNotBlank()) {
            "$primerNombre $segundoNombre $apellido"
        } else {
            "$primerNombre $apellido"
        }
}