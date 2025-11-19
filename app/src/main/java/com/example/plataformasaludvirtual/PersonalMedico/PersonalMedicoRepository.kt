package com.example.plataformasaludvirtual.PersonalMedico


class PersonalMedicoRepository {
    private val apiService = ApiCliente.personalMedicoApiService

    suspend fun getPersonalMedico(): Result<List<PersonalMedico>> {
        return try {
            val personal = apiService.getPersonalMedico()
            Result.success(personal)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}