package com.example.plataformasaludvirtual.personas


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCliente {
    private const val BASE_URL = "https://saludvirtualapi20251107142651-eqezcwgwd7bxf4fm.mexicocentral-01.azurewebsites.net/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val personaApiService: PersonaApiService = retrofit.create(PersonaApiService::class.java)
}