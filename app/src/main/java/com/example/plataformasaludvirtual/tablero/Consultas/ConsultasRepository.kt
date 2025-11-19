package com.example.plataformasaludvirtual.tablero.Consultas

class CubeRepository {
    private val apiService = ApiCliente.cubeApiService



    suspend fun getCitasPorAnio(): Result<List<CitasPorAnio>> {
        return try {
            println("🔄 DEBUG: Llamando a Citas/PorAnio")
            val respuesta = apiService.getCitasPorAnio()
            println("✅ DEBUG: CitasPorAnio - ${respuesta.size} registros")
            Result.success(respuesta)
        } catch (e: Exception) {
            println("❌ DEBUG: Error en Citas/PorAnio: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun getCitasPorTrimestre(): Result<List<CitasEstadoTrimestreDTO>> {
        return try {
            println("🔄 DEBUG: Llamando a Citas/Trimestre2025PorEstado")
            val respuesta = apiService.getCitasEstadoTrimestre()

            println("✅ DEBUG: Datos crudos recibidos: ${respuesta.size} registros")

            // Debug completo de todos los datos
            respuesta.forEachIndexed { index, dato ->
                println("📋 DEBUG CRUDO: [$index] Año=${dato.Anio}, Trimestre='${dato.Trimestre}', Total=${dato.Total}, Confirmadas=${dato.Confirmada}, Canceladas=${dato.Cancelada}")
            }

            // Filtra solo los que tienen trimestre numérico (excluye "All")
            val datosFiltrados = respuesta.filter {
                it.Trimestre != "All" && it.Trimestre.isNotEmpty()
            }

            println("🔍 DEBUG: Después de filtrar 'All': ${datosFiltrados.size} registros")

            // Ordena por trimestre numérico
            val datosOrdenados = datosFiltrados.sortedBy {
                it.Trimestre.toIntOrNull() ?: 0
            }

            println("📊 DEBUG: Datos finales para gráfico: ${datosOrdenados.size} registros")
            datosOrdenados.forEachIndexed { index, dato ->
                println("   [$index] T${dato.Trimestre} - Total:${dato.Total}")
            }

            Result.success(datosOrdenados)
        } catch (e: Exception) {
            println("❌ DEBUG: Error en Trimestre2025PorEstado: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun getRazonesComunesCitas(): Result<List<RazonComunCita>> {
        return try {
            println("🔄 DEBUG: Llamando a RazonesComunesCitas")
            val respuesta = apiService.getRazonesComunesCitas()

            println("✅ DEBUG: RazonesComunesCitas - ${respuesta.size} registros recibidos")

            // Debug detallado de los datos recibidos
            respuesta.forEachIndexed { index, razon ->
                println("📋 DEBUG RAZONES: [$index] Año=${razon.Anio}, Razon='${razon.Razon}', Total_Citas=${razon.Total_Citas}")
            }

            // Filtrar y ordenar los datos
            val datosProcesados = respuesta
                .filter { it.Razon.isNotBlank() } // Excluir razones vacías
                .sortedByDescending { it.Total_Citas } // Ordenar por total de citas descendente

            println("🔍 DEBUG: Después de filtrar y ordenar: ${datosProcesados.size} registros")
            datosProcesados.forEachIndexed { index, razon ->
                println("   [$index] ${razon.Razon} - ${razon.Total_Citas} citas")
            }

            Result.success(datosProcesados)
        } catch (e: Exception) {
            println("❌ DEBUG: Error en RazonesComunesCitas: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
}