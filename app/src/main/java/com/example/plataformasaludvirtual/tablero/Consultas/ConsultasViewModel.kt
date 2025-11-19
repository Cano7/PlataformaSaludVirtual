package com.example.plataformasaludvirtual.tablero.Consultas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CubeViewModel : ViewModel() {
    private val repository = CubeRepository()

    private val _citasPorAnio = MutableStateFlow<List<CitasPorAnio>>(emptyList())
    val citasPorAnio: StateFlow<List<CitasPorAnio>> = _citasPorAnio.asStateFlow()

    private val _citasPorTrimestre = MutableStateFlow<List<CitasEstadoTrimestreDTO>>(emptyList())
    val citasPorTrimestre: StateFlow<List<CitasEstadoTrimestreDTO>> = _citasPorTrimestre.asStateFlow()

    val _razonesComunesCitas = MutableStateFlow<List<RazonComunCita>>(emptyList())
    val razonesComunesCitas: StateFlow<List<RazonComunCita>> = _razonesComunesCitas.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadCitasPorAnio()
        loadCitasPorTrimestre()
        loadRazonesComunesCitas()
    }

    fun loadCitasPorAnio() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val result = repository.getCitasPorAnio()

                if (result.isSuccess) {
                    val datos = result.getOrThrow()
                    _citasPorAnio.value = datos
                    if (datos.isEmpty()) {
                        _error.value = "No hay datos disponibles para citas por año"
                    }
                } else {
                    _error.value = "Error al conectar con la API: ${result.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadCitasPorTrimestre() {
        viewModelScope.launch {
            try {
                println("🔄 VIEWMODEL: Iniciando carga de datos trimestrales...")
                _isLoading.value = true
                val result = repository.getCitasPorTrimestre()

                if (result.isSuccess) {
                    val datos = result.getOrThrow()
                    _citasPorTrimestre.value = datos
                    println("✅ VIEWMODEL: Datos trimestrales asignados: ${datos.size} registros")

                    if (datos.isEmpty()) {
                        println("⚠️ VIEWMODEL: Lista de datos trimestrales está VACÍA después del procesamiento")
                    } else {
                        datos.forEachIndexed { index, dato ->
                            println("🎯 VIEWMODEL FINAL: [$index] T${dato.Trimestre} - Total:${dato.Total}")
                        }
                    }
                } else {
                    println("❌ VIEWMODEL: Error en datos trimestrales: ${result.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                println("❌ VIEWMODEL: Exception en trimestres: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadRazonesComunesCitas() {
        viewModelScope.launch {
            try {
                println("🔄 VIEWMODEL: Iniciando carga de razones comunes de citas...")
                _isLoading.value = true
                _error.value = null

                val result = repository.getRazonesComunesCitas()

                if (result.isSuccess) {
                    val datos = result.getOrThrow()
                    _razonesComunesCitas.value = datos
                    println("✅ VIEWMODEL: Razones comunes asignadas: ${datos.size} registros")

                    if (datos.isEmpty()) {
                        println("⚠️ VIEWMODEL: Lista de razones comunes está VACÍA después del procesamiento")
                        _error.value = "No hay datos disponibles para razones comunes de citas"
                    } else {
                        datos.forEachIndexed { index, razon ->
                            println("🎯 VIEWMODEL RAZONES: [$index] ${razon.Razon} - ${razon.Total_Citas} citas")
                        }
                    }
                } else {
                    val errorMsg = "Error al cargar razones comunes: ${result.exceptionOrNull()?.message}"
                    println("❌ VIEWMODEL: $errorMsg")
                    _error.value = errorMsg
                }
            } catch (e: Exception) {
                val errorMsg = "Error en razones comunes: ${e.message}"
                println("❌ VIEWMODEL: $errorMsg")
                _error.value = errorMsg
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun reloadAllData() {
        loadCitasPorAnio()
        loadCitasPorTrimestre()
        loadRazonesComunesCitas()
    }
}