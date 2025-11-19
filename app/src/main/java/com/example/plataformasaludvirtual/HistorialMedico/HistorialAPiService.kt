package com.example.plataformasaludvirtual.HistorialMedico

import retrofit2.http.GET

interface HistorialApiService {
    @GET("HistorialMedico")
    suspend fun getHistorialMedico(): List<HistorialMedico>
}