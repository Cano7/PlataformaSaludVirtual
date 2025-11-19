package com.example.plataformasaludvirtual.modules.paciente.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plataformasaludvirtual.modules.paciente.model.Paciente
import com.example.plataformasaludvirtual.pacientes.PacienteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PacienteViewModel : ViewModel() {
    private val repository = PacienteRepository()

    private val _pacientes = MutableStateFlow<List<Paciente>>(emptyList())
    val pacientes: StateFlow<List<Paciente>> = _pacientes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadPacientes()
    }

    fun loadPacientes() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getPacientes()
                if (result.isSuccess) {
                    _pacientes.value = result.getOrNull() ?: emptyList()
                } else {
                    _error.value = result.exceptionOrNull()?.message ?: "Error desconocido"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Error de conexión"
            }

            _isLoading.value = false
        }
    }
}