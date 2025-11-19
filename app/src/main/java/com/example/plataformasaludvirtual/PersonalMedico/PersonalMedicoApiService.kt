package com.example.plataformasaludvirtual.PersonalMedico


import retrofit2.http.GET

interface PersonalMedicoApiService {
    @GET("personalmedico")
    suspend fun getPersonalMedico(): List<PersonalMedico>
}