package com.example.plataformasaludvirtual.HistorialMedico

class HistorialRepository {

    suspend fun getHistorialMedico(): Result<List<HistorialMedico>> {
        return try {
            println("DEBUG: Repository - Iniciando obtención de historial médico")

            // Verificar que el ApiCliente esté configurado
            val apiService = try {
                ApiCliente.historialApiService
            } catch (e: Exception) {
                println("DEBUG: Error al obtener apiService - ${e.message}")
                return Result.failure(e)
            }

            println("DEBUG: Repository - Llamando a la API")
            val response = apiService.getHistorialMedico()
            println("DEBUG: Repository - Respuesta recibida, ${response.size} elementos")

            Result.success(response)

        } catch (e: Exception) {
            println("DEBUG: Repository - Error general: ${e.message}")
            Result.failure(e)
        }
    }
}