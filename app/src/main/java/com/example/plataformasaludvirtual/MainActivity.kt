package com.example.plataformasaludvirtual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plataformasaludvirtual.tablero.Consultas.DashboardCuboScreen
import com.example.plataformasaludvirtual.Citas1.CitasScreen
import com.example.plataformasaludvirtual.Estadisticas2.GraficosScreen
import com.example.plataformasaludvirtual.HistorialMedico.HistorialScreen
import com.example.plataformasaludvirtual.PersonalMedico.PersonalMedicoScreen
import com.example.plataformasaludvirtual.login.RegisterScreen
import com.example.plataformasaludvirtual.modules.paciente.screen.PacientesScreen
import com.example.plataformasaludvirtual.modules.persona.screen.PersonasScreen
import com.example.plataformasaludvirtual.screens.LoginScreen
import com.example.plataformasaludvirtual.ui.theme.PlataformaSaludVirtualTheme
import com.example.plataformasaludvirtual.viewmodels.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlataformaSaludVirtualTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginScreen(navController = navController,
                                authViewModel = authViewModel)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController, authViewModel = authViewModel)
                        }

                        composable("inicio") {
                            HomeScreen(
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }

                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }

                        composable("cubo") {
                            DashboardCuboScreen(
                                navController = navController,
                                authViewModel = authViewModel)
                        }

                        // AGREGAR ESTA NUEVA RUTA
                        composable("graficos") {
                            GraficosScreen(
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }

                        composable("paciente") {
                            PacientesScreen(
                                onBackClick = { navController.popBackStack() },
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }

                        composable("citas"){
                            CitasScreen(
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }

                        composable("PersonalMedico"){
                            PersonalMedicoScreen(
                                navController = navController,
                                authViewModel = authViewModel
                            )
                        }

                        composable("Persona"){
                            PersonasScreen(
                                navController = navController,
                                authViewModel = authViewModel)
                        }

                        composable("HistorialMedico"){
                            HistorialScreen(
                                navController= navController,
                                authViewModel = authViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}