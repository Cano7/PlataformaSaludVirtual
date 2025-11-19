package com.example.plataformasaludvirtual.Citas1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plataformasaludvirtual.DrawerHeader
import com.example.plataformasaludvirtual.DrawerSectionTitle
import com.example.plataformasaludvirtual.R
import com.example.plataformasaludvirtual.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitasScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val viewModel: CitasViewModel = viewModel()
    val currentUser by authViewModel.currentUser.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val citas by viewModel.citas.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCitas()
    }



//==========AQUI COMIENZA EL MENÚ================
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                DrawerHeader(currentUser = currentUser)

                DrawerSectionTitle("Navegación")

                NavigationDrawerItem(
                    label = { Text("Inicio", fontSize = 18.sp) },
                    selected = navController.currentDestination?.route == "inicio",
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Inicio"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        if (navController.currentDestination?.route != "home") {
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    }
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) //Estas es la linea que divide las parte del menú


                //INICIO DE LA PARTE DEL CUBO
                DrawerSectionTitle( "Estadistica")

                NavigationDrawerItem(
                    label = { Text("Balances de Citas ", fontSize = 18.sp) },
                    selected = navController.currentDestination?.route == "tablero",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.img_5),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Reportes"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("cubo")
                    }
                )
                //SEGUNDA PANTALLA
                NavigationDrawerItem(
                    label = { Text("Graficas de Datos", fontSize = 18.sp)},
                    selected = navController.currentDestination?.route == "graficos",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.imag_6),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Grafos"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("graficos")
                    }
                )
                //FINALIDAD DE LA PARTE DEL CUBO

                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

//INICIO DE MONGODB
                DrawerSectionTitle("Colecciones")



                NavigationDrawerItem(
                    label = { Text("Pacientes", fontSize = 18.sp) },
                    selected = navController.currentDestination?.route == "paciente",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.img),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Paciente"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("paciente")
                    },
                )


                NavigationDrawerItem(
                    label = { Text("PersonalMedico", fontSize = 18.sp) },
                    selected = navController.currentDestination?.route == "PersonalMedico",
                    icon = {
                        Icon(
                            //Icons.Default.AccountCircle,
                            painter = painterResource(id = R.drawable.icono_medico),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "PersonalMedico"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("PersonalMedico")
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Citas", fontSize = 18.sp) },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.icono_citas),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Citas"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("citas")
                    },
                )


                NavigationDrawerItem(
                    label = { Text("Historial Medico", fontSize = 18.sp) },
                    selected = navController.currentDestination?.route == "HistorialMedico",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.img_4),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Personas"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("HistorialMedico")
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Usuarios Registrados", fontSize = 18.sp) },
                    selected = navController.currentDestination?.route == "Personas",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.img_1),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Personas"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("Persona")
                    }
                )



                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

                // CERRAR SESION
                DrawerSectionTitle("Cerrar Sesion",)
                NavigationDrawerItem(
                    label = { Text("Salir", fontSize = 18.sp) },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        authViewModel.logout()
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        //===========================AQUI LLEGA EL FINAL DE MENÚ=============================

    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Pequeños Héroes",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "Lista de Citas Registradas",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onPrimary,

                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { viewModel.loadCitas() }) {
                    Icon(Icons.Default.Refresh, "Recargar")
                }
            }
        ) { padding ->
            if (isLoading) {
                LoadingState(padding = padding)
            } else if (error != null) {
                ErrorState(error = error!!, onRetry = { viewModel.loadCitas() }, padding = padding)
            } else if (citas.isEmpty()) {
                EmptyState(padding = padding)
            } else {
                CitasList(citas = citas, padding = padding)
            }
        }
    }
}

@Composable
private fun LoadingState(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Cargando citas...", fontSize = 18.sp)
        }
    }
}

@Composable
private fun ErrorState(error: String, onRetry: () -> Unit, padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "❌ Error",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}

@Composable
private fun EmptyState(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Sin citas",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No hay citas registradas",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun CitasList(citas: List<Cita>, padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(padding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Total: ${citas.size} citas",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        itemsIndexed(citas) { index, cita ->
            CitaCard(cita = cita)
        }
    }
}

@Composable
fun CitaCard(cita: Cita) {
    Card(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (cita.estado) {
                "Confirmada" -> MaterialTheme.colorScheme.surface
                "Cancelada" -> MaterialTheme.colorScheme.surface
                else -> MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cita #${cita.codigoCita}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp)

                Text(
                    text = when (cita.estado) {
                        "Confirmada" -> "✅ Confirmada"
                        "Cancelada" -> "❌ Cancelada"
                        else -> cita.estado
                    },
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 16.sp,
                    color = when (cita.estado) {
                        "Confirmada" -> MaterialTheme.colorScheme.primary
                        "Cancelada" -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.outline
                    },
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                CompactInfoRow(
                    label = "Médico:",
                    value = cita.nombreCompletoMedico
                )

                CompactInfoRow(
                    label = "Cargo:",
                    value = cita.nombreCargo
                )

                CompactInfoRow(
                    label = "Paciente:",
                    value = cita.nombreCompletoPaciente
                )

                CompactInfoRow(
                    label = "Edad:",
                    value = "${cita.edadPaciente} años",
                    secondLabel = "Alergias:",
                    secondValue = cita.alergiasFormateadas
                )

                CompactInfoRow(
                    label = "Razón:",
                    value = cita.razon
                )
            }
        }
    }
}

@Composable
fun CompactInfoRow(
    label: String,
    value: String,
    secondLabel: String? = null,
    secondValue: String? = null
) {
    if (secondLabel != null && secondValue != null) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                CompactSingleRow(label = label, value = value)
            }
            Column(modifier = Modifier.weight(1f)) {
                CompactSingleRow(label = secondLabel, value = secondValue)
            }
        }
    } else {
        CompactSingleRow(label = label, value = value)
    }
}

@Composable
fun CompactSingleRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(98.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp
        )
    }
}