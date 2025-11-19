package com.example.plataformasaludvirtual.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class User(
    val username: String = "",
    val email: String = ""
)

class AuthViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    // Credenciales fijas para demo
    private val fixedUsername = "admin"
    private val fixedPassword = "12345678"

    fun register(username: String, email: String, password: String, confirmPassword: String): Boolean {
        return if (password == confirmPassword && username.isNotEmpty() && email.isNotEmpty()) {
            viewModelScope.launch {
                val newUser = User(username, email)
                _currentUser.value = newUser
            }
            true
        } else {
            false
        }
    }

    fun login(username: String, password: String): Boolean {
        val isValid = username == fixedUsername && password == fixedPassword

        if (isValid) {
            viewModelScope.launch {
                _currentUser.value = User(username, "$username@saludvirtual.com")
            }
            return true
        }
        return false
    }

    fun logout() {
        viewModelScope.launch {
            _currentUser.value = null
        }
    }
}