package com.example.plataformasaludvirtual.Estadisticas2

import androidx.compose.ui.graphics.Color

data class CitasSexo2025(
    val Sexo: String,
    val Confirmada: Int,
    val Cancelada: Int,
    val Total_Citas: Int
)
data class ColoresGrafico(
    val confirmadas: Color,
    val canceladas: Color
)

data class ConsultasRespiratorias(
    val Razon: String,
    val Anio: Int,
    val Mes: String,
    val Total_Citas: Int
)
