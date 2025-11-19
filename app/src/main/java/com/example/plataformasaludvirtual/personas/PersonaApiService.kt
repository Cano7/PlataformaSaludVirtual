package com.example.plataformasaludvirtual.personas

import retrofit2.http.GET

interface PersonaApiService {
    @GET("personas")
    suspend fun getPersonas(): List<Persona>
}