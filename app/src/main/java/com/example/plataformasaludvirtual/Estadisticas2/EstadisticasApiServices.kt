package com.example.plataformasaludvirtual.Estadisticas2

import retrofit2.Response
import retrofit2.http.GET

interface EstadisticasApiServices {
    @GET("api/Citas/PorSexo2025PorEstado")
    suspend fun getCitasPorSexo2025PorEstado(): Response<List<CitasSexo2025>>

    @GET("api/Citas/RazonTosMeses")
    suspend fun getCitasRazonTos(): Response<List<ConsultasRespiratorias>>

    @GET("api/Citas/RazonFiebreMeses")
    suspend fun getCitasRazonFiebre(): Response<List<ConsultasRespiratorias>>

    @GET("api/Citas/RazonProblemasRespiratoriosMeses")
    suspend fun getCitasRazonProblemasRespiratorios(): Response<List<ConsultasRespiratorias>>

    @GET("api/Citas/RazonDolorGargantaMeses")
    suspend fun getCitasRazonDolorGarganta(): Response<List<ConsultasRespiratorias>>
}