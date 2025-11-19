package com.example.plataformasaludvirtual.Estadisticas2


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EstadisticasViewModel : ViewModel() {
    private val repository = EstadisticasRepository()

    private val _citasPorSexo2025 = MutableStateFlow<List<CitasSexo2025>>(emptyList())
    val citasPorSexo2025: StateFlow<List<CitasSexo2025>> = _citasPorSexo2025.asStateFlow()

    private val _citasTos = MutableStateFlow<List<ConsultasRespiratorias>>(emptyList())
    val citasTos: StateFlow<List<ConsultasRespiratorias>> = _citasTos.asStateFlow()

    private val _citasFiebre = MutableStateFlow<List<ConsultasRespiratorias>>(emptyList())
    val citasFiebre: StateFlow<List<ConsultasRespiratorias>> = _citasFiebre.asStateFlow()

    private val _citasProblemasRespiratorios = MutableStateFlow<List<ConsultasRespiratorias>>(emptyList())
    val citasProblemasRespiratorios: StateFlow<List<ConsultasRespiratorias>> = _citasProblemasRespiratorios.asStateFlow()

    private val _citasDolorGarganta = MutableStateFlow<List<ConsultasRespiratorias>>(emptyList())
    val citasDolorGarganta: StateFlow<List<ConsultasRespiratorias>> = _citasDolorGarganta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadAllData()
    }

    fun loadAllData() {
        loadCitasPorSexo2025()
        loadConsultasRespiratorias()
    }

    fun loadCitasPorSexo2025() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val result = repository.getCitasPorSexo2025PorEstado()
                if (result.isSuccess) {
                    _citasPorSexo2025.value = result.getOrThrow()
                } else {
                    _error.value = "Error en citas por sexo: ${result.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                _error.value = "Error en citas por sexo: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadConsultasRespiratorias() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val resultTos = repository.getCitasRazonTos()
                val resultFiebre = repository.getCitasRazonFiebre()
                val resultProblemasRespiratorios = repository.getCitasRazonProblemasRespiratorios()
                val resultDolorGarganta = repository.getCitasRazonDolorGarganta()

                if (resultTos.isSuccess) _citasTos.value = resultTos.getOrThrow()
                if (resultFiebre.isSuccess) _citasFiebre.value = resultFiebre.getOrThrow()
                if (resultProblemasRespiratorios.isSuccess) _citasProblemasRespiratorios.value = resultProblemasRespiratorios.getOrThrow()
                if (resultDolorGarganta.isSuccess) _citasDolorGarganta.value = resultDolorGarganta.getOrThrow()

            } catch (e: Exception) {
                _error.value = "Error en consultas respiratorias: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun calcularPorcentajeConfirmadas(sexo: String): Float {
        val datos = getDatosPorSexo(sexo)
        return if (datos != null && datos.Total_Citas > 0) {
            (datos.Confirmada.toFloat() / datos.Total_Citas.toFloat()) * 100
        } else {
            0f
        }
    }

    fun calcularPorcentajeCanceladas(sexo: String): Float {
        val datos = getDatosPorSexo(sexo)
        return if (datos != null && datos.Total_Citas > 0) {
            (datos.Cancelada.toFloat() / datos.Total_Citas.toFloat()) * 100
        } else {
            0f
        }
    }

    fun getDatosPorSexo(sexo: String): CitasSexo2025? {
        return _citasPorSexo2025.value.find { it.Sexo == sexo }
    }

    fun reloadData() {
        loadAllData()
    }


}
