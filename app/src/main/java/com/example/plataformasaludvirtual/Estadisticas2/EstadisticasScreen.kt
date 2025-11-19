package com.example.plataformasaludvirtual.Estadisticas2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plataformasaludvirtual.DrawerHeader
import com.example.plataformasaludvirtual.DrawerSectionTitle
import com.example.plataformasaludvirtual.R
import com.example.plataformasaludvirtual.viewmodels.AuthViewModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraficosScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val viewModel: EstadisticasViewModel = viewModel()
    val currentUser by authViewModel.currentUser.collectAsState()
    val citasPorSexo2025 by viewModel.citasPorSexo2025.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    val citasTos by viewModel.citasTos.collectAsState()
    val citasFiebre by viewModel.citasFiebre.collectAsState()
    val citasProblemasRespiratorios by viewModel.citasProblemasRespiratorios.collectAsState()
    val citasDolorGarganta by viewModel.citasDolorGarganta.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadAllData()
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
                                text = "Estadísticas de Citas",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "Gráficos Estadísticos",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
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
                FloatingActionButton(onClick = {
                    viewModel.reloadData()
                }) {
                    Icon(Icons.Default.Refresh, "Recargar")
                }
            }
        ) { paddingValues ->
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Cargando estadísticas...", fontSize = 18.sp)
                        }
                    }
                }
                error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "❌Error",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 18.sp
                            )
                            Text(
                                text = error!!,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(horizontal = 32.dp),
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
                else -> {
                    EstadisticasCompletoContent(
                        citasPorSexo2025 = citasPorSexo2025,
                        citasTos = citasTos,
                        citasFiebre = citasFiebre,
                        citasProblemasRespiratorios = citasProblemasRespiratorios,
                        citasDolorGarganta = citasDolorGarganta,
                        viewModel = viewModel,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
fun EstadisticasCompletoContent(
    citasPorSexo2025: List<CitasSexo2025>,
    citasTos: List<ConsultasRespiratorias>,
    citasFiebre: List<ConsultasRespiratorias>,
    citasProblemasRespiratorios: List<ConsultasRespiratorias>,
    citasDolorGarganta: List<ConsultasRespiratorias>,
    viewModel: EstadisticasViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (citasPorSexo2025.isNotEmpty()) {
            item {
                Text(
                    text = "Citas por Sexo - 2025",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(citasPorSexo2025) { estadistica ->
                TarjetaEstadisticaSexo(
                    estadistica = estadistica,
                    viewModel = viewModel
                )
            }
        }

        val tieneDatosRespiratorios = citasTos.isNotEmpty() || citasFiebre.isNotEmpty() ||
                citasProblemasRespiratorios.isNotEmpty() || citasDolorGarganta.isNotEmpty()

        if (tieneDatosRespiratorios) {
            item {
                Text(
                    text = "Consultas Respiratorias - Diciembre/Enero 2023-2024",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            if (citasTos.isNotEmpty()) {
                item {
                    TarjetaConsultaRespiratoria(
                        titulo = "Tos",
                        datos = citasTos,
                        colorPrincipal = Color(0xFF4CAF50)
                    )
                }
            }

            if (citasFiebre.isNotEmpty()) {
                item {
                    TarjetaConsultaRespiratoria(
                        titulo = "Fiebre",
                        datos = citasFiebre,
                        colorPrincipal = Color(0xFF3692F4)
                    )
                }
            }

            if (citasProblemasRespiratorios.isNotEmpty()) {
                item {
                    TarjetaConsultaRespiratoria(
                        titulo = "Problemas Respiratorios",
                        datos = citasProblemasRespiratorios,
                        colorPrincipal = Color(0xFF8E54F8)
                    )
                }
            }

            if (citasDolorGarganta.isNotEmpty()) {
                item {
                    TarjetaConsultaRespiratoria(
                        titulo = "Dolor de Garganta",
                        datos = citasDolorGarganta,
                        colorPrincipal = Color(0xFF832C41)
                    )
                }
            }
        }

        if (citasPorSexo2025.isEmpty() && !tieneDatosRespiratorios) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay datos disponibles",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetaConsultaRespiratoria(
    titulo: String,
    datos: List<ConsultasRespiratorias>,
    colorPrincipal: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = colorPrincipal,
                    fontSize = 20.sp
                )
                val total = datos.sumOf { it.Total_Citas }
                Text(
                    text = "$total citas",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = colorPrincipal,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            datos.sortedWith(compareBy({ it.Anio }, {
                when (it.Mes) {
                    "December" -> 1
                    "January" -> 2
                    else -> 3
                }
            })).forEach { dato ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${dato.Mes} ${dato.Anio}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "${dato.Total_Citas} citas",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = colorPrincipal,
                        fontSize = 16.sp
                    )
                }

                val maxCitas = datos.maxOfOrNull { it.Total_Citas }?.toFloat() ?: 1f
                LinearProgressIndicator(
                    progress = (dato.Total_Citas.toFloat() / maxCitas),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = colorPrincipal,
                    trackColor = colorPrincipal.copy(alpha = 0.2f)
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TarjetaEstadisticaSexo(
    estadistica: CitasSexo2025,
    viewModel: EstadisticasViewModel
) {
    val porcentajeConfirmadas = viewModel.calcularPorcentajeConfirmadas(estadistica.Sexo)
    val porcentajeCanceladas = viewModel.calcularPorcentajeCanceladas(estadistica.Sexo)

    val colores = when (estadistica.Sexo.lowercase()) {
        "femenino" -> ColoresGrafico(
            confirmadas = Color(0xFF0064DC),
            canceladas = Color(0xFFFFEB3B)
        )
        "masculino" -> ColoresGrafico(
            confirmadas = Color(0xFF6CD56F),
            canceladas = Color(0xFFFF0E00)
        )
        else -> ColoresGrafico(
            confirmadas = Color(0xFF4CAF50),
            canceladas = Color(0xFFF44336)
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Citas ${estadistica.Sexo}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = when (estadistica.Sexo.lowercase()) {
                    "femenino" -> Color(0xFF000000)
                    "masculino" -> Color(0xFF000000)
                    else -> MaterialTheme.colorScheme.primary
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GraficoPastel(
                confirmadas = estadistica.Confirmada,
                canceladas = estadistica.Cancelada,
                total = estadistica.Total_Citas,
                porcentajeConfirmadas = porcentajeConfirmadas,
                colores = colores,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LeyendaGrafico(
                confirmadas = estadistica.Confirmada,
                canceladas = estadistica.Cancelada,
                porcentajeConfirmadas = porcentajeConfirmadas,
                porcentajeCanceladas = porcentajeCanceladas,
                colores = colores
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Total de Citas: ${estadistica.Total_Citas}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun GraficoPastel(
    confirmadas: Int,
    canceladas: Int,
    total: Int,
    porcentajeConfirmadas: Float,
    colores: ColoresGrafico,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val anguloConfirmadas = (confirmadas.toFloat() / total.toFloat()) * 360f

        androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = colores.confirmadas,
                startAngle = -90f,
                sweepAngle = anguloConfirmadas,
                useCenter = true
            )

            drawArc(
                color = colores.canceladas,
                startAngle = -90f + anguloConfirmadas,
                sweepAngle = 360f - anguloConfirmadas,
                useCenter = true
            )
        }

        Text(
            text = "${porcentajeConfirmadas.toInt()}%",
            fontSize = 26.sp,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun LeyendaGrafico(
    confirmadas: Int,
    canceladas: Int,
    porcentajeConfirmadas: Float,
    porcentajeCanceladas: Float,
    colores: ColoresGrafico
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ItemLeyenda(
            color = colores.canceladas,
            texto = "Canceladas: $canceladas (${porcentajeCanceladas.toInt()}%)"
        )
        ItemLeyenda(
            color = colores.confirmadas,
            texto = "Confirmadas: $confirmadas (${porcentajeConfirmadas.toInt()}%)",
        )
    }
}

@Composable
fun ItemLeyenda(color: Color, texto: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color, CircleShape)
        )
        Text(
            text = texto,
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}