package com.example.plataformasaludvirtual.pacientes

import com.example.plataformasaludvirtual.modules.paciente.model.Paciente

class PacienteRepository {
    private val apiService = ApiClient.pacienteApiService

    suspend fun getPacientes(): Result<List<Paciente>> {
        return try {
            val pacientes = apiService.getPacientes()
            Result.success(pacientes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}