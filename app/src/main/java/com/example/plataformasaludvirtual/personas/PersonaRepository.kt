package com.example.plataformasaludvirtual.personas

class PersonaRepository {
    private val apiService = ApiCliente.personaApiService

    suspend fun getPersonas(): Result<List<Persona>> {
        return try {
            println("DEBUG: Repository - Obteniendo personas de la API")
            val personas = apiService.getPersonas()
            println("DEBUG: Repository - ${personas.size} personas obtenidas")
            Result.success(personas)
        } catch (e: Exception) {
            println("DEBUG: Repository error - ${e.message}")
            Result.failure(e)
        }
    }
}