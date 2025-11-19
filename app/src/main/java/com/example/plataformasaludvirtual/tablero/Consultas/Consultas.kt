package com.example.plataformasaludvirtual.tablero.Consultas

data class CitasPorAnio(
    val Anio: Int = 0,
    val Total_Citas: Int = 0
)

data class CitasEstadoTrimestreDTO(
    val Anio: Int = 0,
    val Cancelada: Int = 0,
    val Confirmada: Int = 0,
    val Total: Int = 0,
    val Trimestre: String = ""
)

data class ResumenEstadistico(
    val totalCitas: Int,
    val añoMaxCitas1: Double,
    val añoMaxCitas: Int,
    val maxCitas: Int,
    val añoMinCitas: Int,
    val minCitas: Int,
    val string: String,
    val d: Double
)

data class RazonComunCita(
    val Anio: Int,
    val Razon: String,
    val Total_Citas: Int,
    val Porcentaje_Citas: Double
)


