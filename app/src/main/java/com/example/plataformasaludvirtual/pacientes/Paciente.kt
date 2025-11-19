package com.example.plataformasaludvirtual.modules.paciente.model

data class Paciente(
    val id: String? = null,
    val codigoPaciente: String,
    val fechaNacimiento: String,
    val alergias: String,
    val activo: Boolean = true,
    val personaId: String? = null,
    val tutorId: String? = null,
    val fechaModificacion: String? = null,
    val primerNombre: String? = null,
    val segundoNombre: String? = null,
    val apellido: String? = null,
    val direccion: String? = null,
    val sexo: String? = null,
    val edad: Int? = null,
    val nombreTutor: String? = null,
    val identificationTutor: String? = null,
    val ocupacionTutor: String? = null
)