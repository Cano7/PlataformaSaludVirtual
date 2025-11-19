package com.example.plataformasaludvirtual.PersonalMedico


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCliente {
    private const val BASE_URL = "https://saludvirtualapi20251107142651-eqezcwgwd7bxf4fm.mexicocentral-01.azurewebsites.net/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val personalMedicoApiService: PersonalMedicoApiService by lazy {
        retrofit.create(PersonalMedicoApiService::class.java)
    }
}