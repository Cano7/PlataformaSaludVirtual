package com.example.plataformasaludvirtual.HistorialMedico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistorialViewModel : ViewModel() {
    private val repository = HistorialRepository()

    private val _historial = MutableStateFlow<List<HistorialMedico>>(emptyList())
    val historial: StateFlow<List<HistorialMedico>> = _historial.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadHistorialMedico()
    }

    fun loadHistorialMedico() {
        viewModelScope.launch {
            try {
                println("DEBUG: ViewModel - Iniciando carga de historial médico")
                _isLoading.value = true
                _error.value = null

                val result = repository.getHistorialMedico()
                println("DEBUG: ViewModel - Resultado del repository: $result")

                if (result.isSuccess) {
                    val historialList = result.getOrThrow()
                    println("DEBUG: ViewModel - Historial cargado: ${historialList.size} registros")
                    _historial.value = historialList
                } else {
                    val exception = result.exceptionOrNull()
                    println("DEBUG: ViewModel - Error cargando historial: ${exception?.message}")
                    _error.value = "Error: ${exception?.message ?: "Desconocido"}"
                }

            } catch (e: Exception) {
                println("DEBUG: ViewModel - Excepción en loadHistorialMedico: ${e.message}")
                _error.value = "Error al cargar historial médico: ${e.message}"
            } finally {
                _isLoading.value = false
                println("DEBUG: ViewModel - Loading finalizado")
            }
        }
    }

}