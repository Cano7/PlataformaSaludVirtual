package com.example.plataformasaludvirtual.Estadisticas2

import retrofit2.Response

class EstadisticasRepository {
    private val estadisticasApiServices: EstadisticasApiServices = ApiCliente.estadisticasApiService

    suspend fun getCitasPorSexo2025PorEstado(): Result<List<CitasSexo2025>> {
        return try {
            println("🔄 REPOSITORY: Haciendo petición a citas por sexo...")
            val response = estadisticasApiServices.getCitasPorSexo2025PorEstado()
            handleResponse(response, "citas por sexo")
        } catch (e: Exception) {
            println("❌ REPOSITORY: Exception - ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getCitasRazonTos(): Result<List<ConsultasRespiratorias>> {
        return try {
            println("🔄 REPOSITORY: Haciendo petición a citas por tos...")
            val response = estadisticasApiServices.getCitasRazonTos()
            handleResponse(response, "citas por tos")
        } catch (e: Exception) {
            println("❌ REPOSITORY: Exception - ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getCitasRazonFiebre(): Result<List<ConsultasRespiratorias>> {
        return try {
            println("🔄 REPOSITORY: Haciendo petición a citas por fiebre...")
            val response = estadisticasApiServices.getCitasRazonFiebre()
            handleResponse(response, "citas por fiebre")
        } catch (e: Exception) {
            println("❌ REPOSITORY: Exception - ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getCitasRazonProblemasRespiratorios(): Result<List<ConsultasRespiratorias>> {
        return try {
            println("🔄 REPOSITORY: Haciendo petición a citas por problemas respiratorios...")
            val response = estadisticasApiServices.getCitasRazonProblemasRespiratorios()
            handleResponse(response, "citas por problemas respiratorios")
        } catch (e: Exception) {
            println("❌ REPOSITORY: Exception - ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getCitasRazonDolorGarganta(): Result<List<ConsultasRespiratorias>> {
        return try {
            println("🔄 REPOSITORY: Haciendo petición a citas por dolor de garganta...")
            val response = estadisticasApiServices.getCitasRazonDolorGarganta()
            handleResponse(response, "citas por dolor de garganta")
        } catch (e: Exception) {
            println("❌ REPOSITORY: Exception - ${e.message}")
            Result.failure(e)
        }
    }

    private fun <T> handleResponse(response: Response<T>, endpoint: String): Result<T> {
        return if (response.isSuccessful) {
            val datos = response.body()
            println("✅ REPOSITORY: Respuesta exitosa de $endpoint, ${(datos as? List<*>)?.size ?: 1} registros")

            if (datos != null) {
                Result.success(datos)
            } else {
                when (endpoint) {
                    "citas por sexo" -> Result.success(emptyList<CitasSexo2025>() as T)
                    "citas por tos" -> Result.success(emptyList<ConsultasRespiratorias>() as T)
                    "citas por fiebre" -> Result.success(emptyList<ConsultasRespiratorias>() as T)
                    "citas por problemas respiratorios" -> Result.success(emptyList<ConsultasRespiratorias>() as T)
                    "citas por dolor de garganta" -> Result.success(emptyList<ConsultasRespiratorias>() as T)
                    else -> Result.success(emptyList<Any>() as T)
                }
            }
        } else {
            val errorMsg = "Error HTTP en $endpoint: ${response.code()} - ${response.message()}"
            println("❌ REPOSITORY: $errorMsg")
            Result.failure(Exception(errorMsg))
        }
    }
}