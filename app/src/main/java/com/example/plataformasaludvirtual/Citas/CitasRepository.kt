package com.example.plataformasaludvirtual.Citas1

import com.example.plataformasaludvirtual.PersonalMedico.PersonalMedico


class CitasRepository {
    private val apiService = ApiCliente.citasApiService
    suspend fun getCitas (): Result<List<Cita>>{
        return try {
            val cita = apiService.getCitas()
            Result.success(cita)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}