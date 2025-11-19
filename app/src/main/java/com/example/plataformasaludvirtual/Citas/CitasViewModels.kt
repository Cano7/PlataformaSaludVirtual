package com.example.plataformasaludvirtual.Citas1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CitasViewModel : ViewModel() {

    private val repository = CitasRepository()

    private val _citas = MutableStateFlow<List<Cita>>(emptyList())
    val citas: StateFlow<List<Cita>> = _citas.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadCitas() {
        viewModelScope.launch {
            try {
                println("DEBUG: Iniciando carga de citas...")
                _isLoading.value = true
                _error.value = null

                val result = repository.getCitas()
                println("DEBUG: Resultado del repository: $result")

                if (result.isSuccess) {
                    val citasList = result.getOrThrow()
                    println("DEBUG: Citas cargadas: ${citasList.size}")
                    _citas.value = citasList
                } else {
                    val exception = result.exceptionOrNull()
                    println("DEBUG: Error cargando citas: ${exception?.message}")
                    _error.value = "Error: ${exception?.message ?: "Desconocido"}"
                }

            } catch (e: Exception) {
                println("DEBUG: Excepción en loadCitas: ${e.message}")
                _error.value = "Error al cargar citas: ${e.message}"
            } finally {
                _isLoading.value = false
                println("DEBUG: Loading finalizado")
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}