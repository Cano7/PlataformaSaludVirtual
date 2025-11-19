package com.example.plataformasaludvirtual.Citas1
import retrofit2.http.GET

interface CitasApiService {
    @GET("citas")
    suspend fun getCitas(): List<Cita>

}