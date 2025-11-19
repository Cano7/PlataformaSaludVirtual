package com.example.plataformasaludvirtual.tablero.Consultas

import retrofit2.http.GET

interface CubeApiService {
    @GET("Citas/PorAnio")
    suspend fun getCitasPorAnio(): List<CitasPorAnio>

    @GET("Citas/Trimestre2025PorEstado")
    suspend fun getCitasEstadoTrimestre(): List<CitasEstadoTrimestreDTO>

    @GET("Citas/Top6Razon2024")
    suspend fun getRazonesComunesCitas(): List<RazonComunCita>
}