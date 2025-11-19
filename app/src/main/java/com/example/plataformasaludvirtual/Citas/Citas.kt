package com.example.plataformasaludvirtual.Citas1

data class Cita(
    val codigoCita: String = "",
    val razon: String = "",
    val estado: String = "",
    val fechahora: String = "",
    val activo: Boolean = true,
    val primerNombrePaciente: String = "",
    val apellidoPaciente: String = "",
    val edadPaciente: Int = 0,
    val alergiasPaciente: String = "",
    val primerNombreMedico: String = "",
    val apellidoMedico: String = "",
    val nombreCargo: String = ""
) {
    val nombreCompletoPaciente: String
        get() = "$primerNombrePaciente $apellidoPaciente"

    val nombreCompletoMedico: String
        get() = "$primerNombreMedico $apellidoMedico"

    val alergiasFormateadas: String
        get() = if (alergiasPaciente.isBlank()) "Ninguna" else alergiasPaciente

    val fechaHora: String
        get() = formatDateTime(fechahora)
}

fun formatDateTime(dateTimeString: String): String {
    return try {
        if (dateTimeString.isBlank()) return "No especificada"

        val invalidDates = listOf(
            "0001-01-01T00:00:00",
            "0001-01-01",
            "1900-01-01",
            "1970-01-01",
            "0001-01-01100:00:00"
        )

        if (invalidDates.any { dateTimeString.contains(it) }) {
            return "No especificada"
        }

        val formats = listOf(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "dd/MM/yyyy HH:mm:ss",
            "dd/MM/yyyy"
        )

        for (format in formats) {
            try {
                val inputFormat = java.text.SimpleDateFormat(format, java.util.Locale.getDefault())
                val date = inputFormat.parse(dateTimeString)
                if (date != null) {
                    val outputFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
                    return outputFormat.format(date)
                }
            } catch (e: Exception) {
            }
        }

        "No especificada"
    } catch (e: Exception) {
        "No especificada"
    }
}