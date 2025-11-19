package com.example.plataformasaludvirtual.personas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PersonasViewModel : ViewModel() {
    private val repository = PersonaRepository()

    private val _personas = MutableStateFlow<List<Persona>>(emptyList())
    val personas: StateFlow<List<Persona>> = _personas.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun cargarPersonas() {
        viewModelScope.launch {
            try {
                println("DEBUG: ViewModel - Iniciando carga de personas")
                _isLoading.value = true
                _error.value = null

                val result = repository.getPersonas()
                println("DEBUG: ViewModel - Resultado del repository: $result")

                if (result.isSuccess) {
                    val personasList = result.getOrThrow()
                    println("DEBUG: ViewModel - Personas cargadas: ${personasList.size}")
                    _personas.value = personasList
                } else {
                    val exception = result.exceptionOrNull()
                    println("DEBUG: ViewModel - Error cargando personas: ${exception?.message}")
                    _error.value = "Error: ${exception?.message ?: "Desconocido"}"
                }

            } catch (e: Exception) {
                println("DEBUG: ViewModel - Excepción en cargarPersonas: ${e.message}")
                _error.value = "Error al cargar personas: ${e.message}"
            } finally {
                _isLoading.value = false
                println("DEBUG: ViewModel - Loading finalizado")
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}