package com.example.plataformasaludvirtual.PersonalMedico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PersonalMedicoViewModel : ViewModel() {

    private val repository = PersonalMedicoRepository()

    private val _personalMedico = MutableStateFlow<List<PersonalMedico>>(emptyList())
    val personalMedico: StateFlow<List<PersonalMedico>> = _personalMedico.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadPersonalMedico() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val result = repository.getPersonalMedico()
                if (result.isSuccess) {
                    _personalMedico.value = result.getOrDefault(emptyList())
                } else {
                    _error.value = "Error: ${result.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                _error.value = "Error al cargar personal médico: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}