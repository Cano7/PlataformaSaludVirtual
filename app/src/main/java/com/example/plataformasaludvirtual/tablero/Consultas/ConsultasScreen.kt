package com.example.plataformasaludvirtual.tablero.Consultas


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import kotlinx.coroutines.delay
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.plataformasaludvirtual.DrawerHeader
import com.example.plataformasaludvirtual.DrawerSectionTitle
import com.example.plataformasaludvirtual.R
import com.example.plataformasaludvirtual.viewmodels.AuthViewModel
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardCuboScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val viewModel: CubeViewModel = viewModel()
    val currentUser by authViewModel.currentUser.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val citasPorAnio by viewModel.citasPorAnio.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val citasPorTrimestre by viewModel.citasPorTrimestre.collectAsState()
    val razonesComunes by viewModel.razonesComunesCitas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRazonesComunesCitas()
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
                                text = "Graficos Estadisticos",
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
                FloatingActionButton(onClick = {
                    viewModel.reloadAllData()
                }) {
                    Icon(Icons.Default.Refresh, "Recargar")
                }
            }

        ) { padding ->
            if (isLoading) {
                LoadingState(padding = padding)
            } else if (error != null) {
                ErrorState(error = error!!, onRetry = { viewModel.reloadAllData() }, padding = padding)
            } else if (citasPorAnio.isEmpty() && citasPorTrimestre.isEmpty()) {
                EmptyState(padding = padding)
            } else {
                GraficosList(
                    citasPorAnio = citasPorAnio,
                    datosTrimestres = citasPorTrimestre,
                    razonesComunes = razonesComunes,
                    isLoadingRazones = isLoading,
                    padding = padding,
                    viewModel = viewModel
                )
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
            Text("Cargando datos del cubo...", fontSize = 18.sp)
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
                text = "❌Error",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error
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
                Icons.Default.Refresh,
                contentDescription = "Sin datos",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No hay datos disponibles",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun GraficosList(
    citasPorAnio: List<CitasPorAnio>,
    datosTrimestres: List<CitasEstadoTrimestreDTO>,
    razonesComunes: List<RazonComunCita>,
    isLoadingRazones: Boolean,
    padding: PaddingValues,
    viewModel: CubeViewModel
) {
    val resumen = calcularResumenEstadistico(citasPorAnio)

    LazyColumn(
        modifier = Modifier.padding(padding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ResumenEstadisticosCard(resumen = resumen)
        }
        item {
            GraficoBarrasHorizontales(citasPorAnio = citasPorAnio)
        }

        item {
            GraficoLineasCitasTrimestres(datos = datosTrimestres)
        }

        item {
            if (razonesComunes.isNotEmpty()) {
                GraficoBarrasRazonesComunes(razonesComunes = razonesComunes)
            } else if (isLoadingRazones) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun GraficoBarrasHorizontales(citasPorAnio: List<CitasPorAnio>) {
    val datosOrdenados = citasPorAnio.sortedBy { it.Anio }
    val maxCitas = datosOrdenados.maxOfOrNull { it.Total_Citas } ?: 1

    val animatedWidths = remember {
        mutableMapOf<Int, Animatable<Float, *>>()
    }

    var animationStarted by remember { mutableStateOf(false) }

    LaunchedEffect(animationStarted) {
        if (animationStarted) {
            datosOrdenados.forEachIndexed { index, dato ->
                val targetWidth = (dato.Total_Citas.toFloat() / maxCitas) * 1f
                animatedWidths[index]?.animateTo(
                    targetValue = targetWidth,
                    animationSpec = spring(
                        dampingRatio = 0.6f,
                        stiffness = 300f
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        datosOrdenados.forEachIndexed { index, _ ->
            animatedWidths[index] = Animatable(0f)
        }
        delay(300)
        animationStarted = true
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Total de Citas por Año",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 40.dp, end = 10.dp, top = 10.dp, bottom = 40.dp)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    val steps = 4
                    val stepWidth = canvasWidth / steps

                    for (i in 0..steps) {
                        val x = i * stepWidth
                        drawLine(
                            color = Color(0xFFE0E0E0),
                            start = Offset(x, 0f),
                            end = Offset(x, canvasHeight),
                            strokeWidth = 1f
                        )

                        val valor = (i * maxCitas / steps)
                        drawContext.canvas.nativeCanvas.drawText(
                            valor.toString(),
                            x,
                            canvasHeight + 25f,
                            Paint().apply {
                                color = android.graphics.Color.parseColor("#000000")
                                textSize = 26f
                                textAlign = Paint.Align.CENTER
                                isFakeBoldText = true
                            }
                        )
                    }

                    val barHeight = canvasHeight / datosOrdenados.size * 0.6f
                    val espacioEntreBarras = canvasHeight / datosOrdenados.size * 0.4f

                    datosOrdenados.forEachIndexed { index, dato ->
                        val currentWidth = animatedWidths[index]?.value ?: 0f
                        val barWidth = currentWidth * canvasWidth
                        val y = index * (barHeight + espacioEntreBarras)

                        drawRect(
                            color = Color(0xFF4CAF50),
                            topLeft = Offset(0f, y),
                            size = Size(barWidth, barHeight)
                        )

                        drawContext.canvas.nativeCanvas.drawText(
                            dato.Anio.toString(),
                            -10f,
                            y + barHeight / 2 + 10f,
                            Paint().apply {
                                color = android.graphics.Color.parseColor("#000000")
                                textSize = 26f
                                textAlign = Paint.Align.RIGHT
                                isFakeBoldText = true
                            }
                        )

                        if (barWidth > 50f) {
                            drawContext.canvas.nativeCanvas.drawText(
                                dato.Total_Citas.toString(),
                                barWidth + 1f,
                                y + barHeight / 2 + 10f,
                                Paint().apply {
                                    color = android.graphics.Color.parseColor("#000000")
                                    textSize = 26f
                                    textAlign = Paint.Align.LEFT
                                    isFakeBoldText = true
                                }
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .padding(bottom = 40.dp, top = 10.dp)
                        .align(Alignment.CenterStart),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawContext.canvas.nativeCanvas.drawText(
                            "Años",
                            -size.height / 2,
                            20f,
                            Paint().apply {
                                color = android.graphics.Color.parseColor("#666666")
                                textSize = 26f
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }
            }
        }
    }
}
fun calcularResumenEstadistico(citasPorAnio: List<CitasPorAnio>): ResumenEstadistico {
    if (citasPorAnio.isEmpty()) {
        return ResumenEstadistico(0, 0.0, 0, 0, 0, 0, "", 0.0)
    }

    val datosOrdenados = citasPorAnio.sortedBy { it.Anio }

    val totalCitas = datosOrdenados.sumOf { it.Total_Citas }

    val añoMax = datosOrdenados.maxByOrNull { it.Total_Citas }?.Anio ?: 0
    val maxCitas = datosOrdenados.maxOfOrNull { it.Total_Citas } ?: 0

    val añoMin = datosOrdenados.minByOrNull { it.Total_Citas }?.Anio ?: 0
    val minCitas = datosOrdenados.minOfOrNull { it.Total_Citas } ?: 0

    return ResumenEstadistico(
        totalCitas = totalCitas,
        añoMaxCitas1 = 0.0,
        añoMaxCitas = añoMax,
        maxCitas = maxCitas,
        añoMinCitas = añoMin,
        minCitas = minCitas,
        string = "",
        d = 0.0,
    )
}

@Composable
fun ResumenEstadisticosCard(resumen: ResumenEstadistico) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Resumen Estadístico",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF000000),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Total Citas",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp,
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "${resumen.totalCitas}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Máximo",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "${resumen.maxCitas}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "Año ${resumen.añoMaxCitas}",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp,
                        color = Color(0xFF000000)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Mínimo",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "${resumen.minCitas}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "Año ${resumen.añoMinCitas}",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp,
                        color = Color(0xFF000000)
                    )
                }
            }
        }
    }
}
@Composable
fun GraficoLineasCitasTrimestres(datos: List<CitasEstadoTrimestreDTO>) {
    val datosTrimestres = datos.sortedBy { it.Trimestre.toIntOrNull() ?: 0 }

    if (datosTrimestres.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No hay datos trimestrales disponibles", fontSize = 18.sp)
            Text("Total de datos recibidos: ${datos.size}", fontSize = 16.sp)
        }
        return
    }

    val totalCitasAnual = datosTrimestres.sumOf { it.Total }
    val totalConfirmadasAnual = datosTrimestres.sumOf { it.Confirmada }
    val totalCanceladasAnual = datosTrimestres.sumOf { it.Cancelada }
    val maxCitas = datosTrimestres.maxOfOrNull { it.Total } ?: 1
    val minCitas = datosTrimestres.minOfOrNull {
        minOf(it.Cancelada, it.Confirmada, it.Total)
    } ?: 0

    var tooltipState by remember { mutableStateOf<TooltipData?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Citas por Trimestre - ${datosTrimestres.first().Anio}",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            ResumenTrimestralCard(
                totalCitas = totalCitasAnual,
                totalConfirmadas = totalConfirmadasAnual,
                totalCanceladas = totalCanceladasAnual,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                val canvasPaddingStart = 60.dp.toPx()
                                val canvasPaddingEnd = 25.dp.toPx()
                                val canvasPaddingTop = 15.dp.toPx()
                                val canvasPaddingBottom = 50.dp.toPx()

                                val canvasWidth = size.width - canvasPaddingStart - canvasPaddingEnd
                                val canvasHeight = size.height - canvasPaddingTop - canvasPaddingBottom

                                val puntoWidth = canvasWidth / (datosTrimestres.size - 1).coerceAtLeast(1)
                                val rangeCitas = maxCitas - minCitas

                                val adjustedX = offset.x - canvasPaddingStart
                                val adjustedY = offset.y - canvasPaddingTop

                                var closestPoint: TooltipData? = null
                                var minDistance = 40f

                                datosTrimestres.forEachIndexed { index, dato ->
                                    val x = index * puntoWidth

                                    val yTotal = canvasHeight - ((dato.Total - minCitas).toFloat() / rangeCitas * canvasHeight)
                                    val yConfirmadas = canvasHeight - ((dato.Confirmada - minCitas).toFloat() / rangeCitas * canvasHeight)
                                    val yCanceladas = canvasHeight - ((dato.Cancelada - minCitas).toFloat() / rangeCitas * canvasHeight)

                                    val distanceTotal = sqrt((adjustedX - x).pow(2) + (adjustedY - yTotal).pow(2))
                                    val distanceConfirmadas = sqrt((adjustedX - x).pow(2) + (adjustedY - yConfirmadas).pow(2))
                                    val distanceCanceladas = sqrt((adjustedX - x).pow(2) + (adjustedY - yCanceladas).pow(2))

                                    if (distanceTotal < minDistance) {
                                        minDistance = distanceTotal
                                        closestPoint = TooltipData(
                                            position = Offset(x + canvasPaddingStart, yTotal + canvasPaddingTop),
                                            trimestre = dato.Trimestre,
                                            total = dato.Total,
                                            confirmadas = dato.Confirmada,
                                            canceladas = dato.Cancelada,
                                            tipo = "Total"
                                        )
                                    }
                                    if (distanceConfirmadas < minDistance) {
                                        minDistance = distanceConfirmadas
                                        closestPoint = TooltipData(
                                            position = Offset(x + canvasPaddingStart, yConfirmadas + canvasPaddingTop),
                                            trimestre = dato.Trimestre,
                                            total = dato.Total,
                                            confirmadas = dato.Confirmada,
                                            canceladas = dato.Cancelada,
                                            tipo = "Confirmadas"
                                        )
                                    }
                                    if (distanceCanceladas < minDistance) {
                                        minDistance = distanceCanceladas
                                        closestPoint = TooltipData(
                                            position = Offset(x + canvasPaddingStart, yCanceladas + canvasPaddingTop),
                                            trimestre = dato.Trimestre,
                                            total = dato.Total,
                                            confirmadas = dato.Confirmada,
                                            canceladas = dato.Cancelada,
                                            tipo = "Canceladas"
                                        )
                                    }
                                }

                                tooltipState = if (closestPoint != null) {
                                    closestPoint
                                } else {
                                    null
                                }
                            }
                        }
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 60.dp, end = 25.dp, top = 15.dp, bottom = 50.dp)
                    ) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        val rangeCitas = maxCitas - minCitas

                        val steps = 4
                        val stepHeight = canvasHeight / steps

                        for (i in 0..steps) {
                            val y = canvasHeight - (i * stepHeight)
                            drawLine(
                                color = Color(0xFFE0E0E0),
                                start = Offset(0f, y),
                                end = Offset(canvasWidth, y),
                                strokeWidth = 1f
                            )

                            val valor = minCitas + (i * rangeCitas / steps)
                            drawContext.canvas.nativeCanvas.drawText(
                                valor.toString(),
                                -50f,
                                y + 10f,
                                android.graphics.Paint().apply {
                                    color = android.graphics.Color.parseColor("#000000")
                                    textSize = 26f
                                    textAlign = android.graphics.Paint.Align.RIGHT
                                    isFakeBoldText = true
                                }
                            )
                        }

                        val puntosTotal = mutableListOf<Offset>()
                        val puntosConfirmadas = mutableListOf<Offset>()
                        val puntosCanceladas = mutableListOf<Offset>()

                        val puntoWidth = canvasWidth / (datosTrimestres.size - 1).coerceAtLeast(1)

                        datosTrimestres.forEachIndexed { index, dato ->
                            val x = index * puntoWidth

                            val yTotal = canvasHeight - ((dato.Total - minCitas).toFloat() / rangeCitas * canvasHeight)
                            puntosTotal.add(Offset(x, yTotal))

                            val yConfirmadas = canvasHeight - ((dato.Confirmada - minCitas).toFloat() / rangeCitas * canvasHeight)
                            puntosConfirmadas.add(Offset(x, yConfirmadas))

                            val yCanceladas = canvasHeight - ((dato.Cancelada - minCitas).toFloat() / rangeCitas * canvasHeight)
                            puntosCanceladas.add(Offset(x, yCanceladas))

                            drawContext.canvas.nativeCanvas.drawText(
                                "T${dato.Trimestre}",
                                x,
                                canvasHeight + 35f,
                                android.graphics.Paint().apply {
                                    color = android.graphics.Color.parseColor("#000000")
                                    textSize = 26f
                                    textAlign = android.graphics.Paint.Align.CENTER
                                    isFakeBoldText = true
                                }
                            )
                        }

                        dibujarLinea(puntosTotal, Color(0xFF2196F3), 10f)
                        dibujarLinea(puntosConfirmadas, Color(0xFF4CAF50), 10f)
                        dibujarLinea(puntosCanceladas, Color(0xFFF44336), 10f)

                        puntosTotal.forEach { punto ->
                            drawCircle(color = Color(0xFF2196F3), center = punto, radius = 15f)
                            drawCircle(color = Color.White, center = punto, radius = 5f)
                        }
                        puntosConfirmadas.forEach { punto ->
                            drawCircle(color = Color(0xFF4CAF50), center = punto, radius = 12f)
                            drawCircle(color = Color.White, center = punto, radius = 4f)
                        }
                        puntosCanceladas.forEach { punto ->
                            drawCircle(color = Color(0xFFF44336), center = punto, radius = 12f)
                            drawCircle(color = Color.White, center = punto, radius = 4f)
                        }
                    }

                    tooltipState?.let { tooltip ->
                        TooltipComposable(
                            position = tooltip.position,
                            data = tooltip
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Column {
                        LeyendaItem("Total", Color(0xFF2196F3))
                        LeyendaItem("Confirmadas", Color(0xFF4CAF50))
                        LeyendaItem("Canceladas", Color(0xFFF44336))
                    }
                }
            }
        }
    }
}

@Composable
fun TooltipComposable(position: Offset, data: TooltipData) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    val color = when (data.tipo) {
        "Total" -> Color(0xFF2196F3)
        "Confirmadas" -> Color(0xFF4CAF50)
        "Canceladas" -> Color(0xFFF44336)
        else -> Color(0xFF000000)
    }

    val tooltipWidth = 200.dp
    val tooltipHeight = 140.dp
    val screenPadding = 16.dp

    val xPosition = remember(position, screenWidthDp) {
        val maxX = screenWidthDp - tooltipWidth - screenPadding
        if (position.x.dp > maxX) maxX else position.x.dp
    }

    val yPosition = remember(position) {
        val maxY = 550.dp - tooltipHeight - screenPadding
        if (position.y.dp > maxY) position.y.dp - tooltipHeight - 20.dp else position.y.dp - tooltipHeight - 20.dp
    }

    Box(
        modifier = Modifier
            .offset(x = xPosition, y = yPosition)
            .width(tooltipWidth)
            .background(Color.White, MaterialTheme.shapes.medium)
            .shadow(8.dp, MaterialTheme.shapes.medium)
            .border(2.dp, color, MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Trimestre T${data.trimestre}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "• Total: ${data.total} citas",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF2196F3),
                fontSize = 16.sp
            )
            Text(
                text = "• Confirmadas: ${data.confirmadas}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF4CAF50),
                fontSize = 16.sp
            )
            Text(
                text = "• Canceladas: ${data.canceladas}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFF44336),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

data class TooltipData(
    val position: Offset,
    val trimestre: String,
    val total: Int,
    val confirmadas: Int,
    val canceladas: Int,
    val tipo: String
)

@Composable
fun ResumenTrimestralCard(
    totalCitas: Int,
    totalConfirmadas: Int,
    totalCanceladas: Int
) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MetricItem("Total", totalCitas.toString(), Color(0xFF2196F3))
            MetricItem("Confirmadas", totalConfirmadas.toString(), Color(0xFF4CAF50))
            MetricItem("Canceladas", totalCanceladas.toString(), Color(0xFFF44336))
        }
}

@Composable
fun MetricItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF666666)
        )
    }
}

fun DrawScope.dibujarLinea(puntos: List<Offset>, color: Color, strokeWidth: Float) {
    if (puntos.size < 2) return

    for (i in 0 until puntos.size - 1) {
        drawLine(
            color = color,
            start = puntos[i],
            end = puntos[i + 1],
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}
@Composable
fun LeyendaItem(texto: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = texto,
            fontSize = 14.sp,
            color = Color(0xFF333333)
        )
    }
}

@Composable
fun GraficoBarrasRazonesComunes(razonesComunes: List<RazonComunCita>) {
    if (razonesComunes.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No hay datos para mostrar")
        }
        return
    }

    val maxCitas = razonesComunes.maxOfOrNull { it.Total_Citas } ?: 1

    val animatedHeights = remember {
        mutableMapOf<Int, Animatable<Float, *>>()
    }

    var animationStarted by remember { mutableStateOf(false) }

    LaunchedEffect(razonesComunes) {
        razonesComunes.forEachIndexed { index, _ ->
            animatedHeights[index] = Animatable(0f)
        }
        delay(300)
        animationStarted = true
    }

    LaunchedEffect(animationStarted) {
        if (animationStarted) {
            razonesComunes.forEachIndexed { index, razon ->
                val targetHeight = (razon.Total_Citas.toFloat() / maxCitas) * 1f
                animatedHeights[index]?.animateTo(
                    targetValue = targetHeight,
                    animationSpec = spring(
                        dampingRatio = 0.6f,
                        stiffness = 300f
                    )
                )
            }
        }
    }
    val colores = listOf(
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFFF9800),
        Color(0xFF9C27B0),
        Color(0xFFF44336),
        Color(0xFF00BCD4),
    )



    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {

        Text(
            text = "Razones Comunes",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 60.dp, end = 10.dp, top = 10.dp, bottom = 40.dp)
                    ) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height

                        val steps = 5
                        val stepHeight = canvasHeight / steps

                        for (i in 0..steps) {
                            val y = canvasHeight - (i * stepHeight)
                            drawLine(
                                color = Color(0xFFE0E0E0),
                                start = Offset(0f, y),
                                end = Offset(canvasWidth, y),
                                strokeWidth = 1f
                            )

                            val valor = (i * maxCitas / steps)
                            drawContext.canvas.nativeCanvas.drawText(
                                valor.toString(),
                                -50f,
                                y + 10f,
                                Paint().apply {
                                    color = android.graphics.Color.parseColor("#000000")
                                    textSize = 26f
                                    textAlign = Paint.Align.RIGHT
                                    isFakeBoldText = true
                                }
                            )
                        }

                        val barWidth = canvasWidth / razonesComunes.size * 0.7f
                        val espacioEntreBarras = canvasWidth / razonesComunes.size * 0.3f

                        razonesComunes.forEachIndexed { index, razon ->
                            val currentHeight = animatedHeights[index]?.value ?: 0f
                            val barHeight = currentHeight * canvasHeight
                            val x = index * (barWidth + espacioEntreBarras)

                            val color = colores.getOrElse(index) { Color(0xFF666666) }

                            drawRoundRect(
                                color = color,
                                topLeft = Offset(
                                    x,
                                    canvasHeight - barHeight
                                ),
                                size = Size(barWidth, barHeight),
                                cornerRadius = CornerRadius(4f, 4f)
                            )

                            val textoRazon = if (razon.Razon.length > 15) {
                                "${razon.Razon.substring(0, 12)}..."
                            } else {
                                razon.Razon
                            }

                            if (barHeight > 30f) {
                                drawContext.canvas.nativeCanvas.drawText(
                                    razon.Total_Citas.toString(),
                                    x + barWidth / 2,
                                    canvasHeight - barHeight - 10f,
                                    Paint().apply {
                                        textSize = 26f
                                        textAlign = Paint.Align.CENTER
                                        isFakeBoldText = true
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Leyenda - Significado de Colores",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                val mitad = (razonesComunes.size + 1) / 2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        razonesComunes.take(mitad).forEachIndexed { index, razon ->
                            LeyendaItem(
                                texto = razon.Razon,
                                color = colores[index],
                                totalCitas = razon.Total_Citas
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        razonesComunes.drop(mitad).forEachIndexed { index, razon ->
                            LeyendaItem(
                                texto = razon.Razon,
                                color = colores[mitad + index],
                                totalCitas = razon.Total_Citas
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LeyendaItem(texto: String, color: Color, totalCitas: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, RoundedCornerShape(4.dp))
                .border(1.dp, Color(0xFFCCCCCC), RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = texto,
                fontSize = 14.sp,
                color = Color(0xFF333333),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$totalCitas citas",
                fontSize = 12.sp,
                color = Color(0xFF666666)
            )
        }
    }
}