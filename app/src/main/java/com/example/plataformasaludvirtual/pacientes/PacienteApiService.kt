package com.example.plataformasaludvirtual.pacientes

import com.example.plataformasaludvirtual.modules.paciente.model.Paciente
import retrofit2.http.GET

interface PacienteApiService {
    @GET("pacientes")
    suspend fun getPacientes(): List<Paciente>
}