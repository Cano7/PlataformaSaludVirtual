package com.example.plataformasaludvirtual.home.screen

import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plataformasaludvirtual.viewmodels.AuthViewModel
import com.example.plataformasaludvirtual.viewmodels.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

//==========AQUI COMIENZA EL MENÚ================
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader(currentUser = currentUser)

                DrawerSectionTitle("Navegación")

                NavigationDrawerItem(
                    label = { Text("Inicio") },
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

                NavigationDrawerItem(
                    label = { Text("Citas") },
                    selected = navController.currentDestination?.route == "citas",
                    icon = {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Citas"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("citas")
                    }
                )

                // Línea divisoria
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

                // Sección: Estadísticas
                DrawerSectionTitle("Estadísticas")

                // ✅ SEGUNDO ÍTEM:
                NavigationDrawerItem(
                    label = { Text("Dashboard del cubo ") },
                    selected = navController.currentDestination?.route == "tablero",
                    icon = {
                        Icon(
                            Icons.Default.AccountBox, //icono el AccountBox, (cambiar luego)
                            contentDescription = "Reportes"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        // ✅ Navegar al tablero gerencial
                        navController.navigate("tablero")
                    }
                )

                // Línea divisoria
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

                // Sección: Colecciones
                DrawerSectionTitle("Colecciones")
                NavigationDrawerItem(
                    label = { Text("Citas") },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Citas"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                    },
                )

                NavigationDrawerItem(
                    label = { Text("Paciente") },
                    selected = navController.currentDestination?.route == "paciente",
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Paciente"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("paciente")
                    },
                )

                NavigationDrawerItem(
                    label = { Text("PersonalMedico") },
                    selected = navController.currentDestination?.route == "PersonalMedico",
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "PersonalMedico"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Aquí navegaríamos a citas (próximo paso)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Personas") },
                    selected = navController.currentDestination?.route == "Personas",
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Personas"
                        )
                    },
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Aquí navegaríamos a citas (próximo paso)
                    }
                )

                // Línea divisoria
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))

                // Sección: Sesión
                DrawerSectionTitle("Sesión")
                NavigationDrawerItem(
                    label = { Text("Cerrar sesión") },
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
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "Hospital Pediatrico Especializado",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onPrimary,
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
                    actions = {
                        IconButton(onClick = {
                            // Aquí podríamos navegar al perfil
                        }) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Perfil",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo de la aplicación
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Pequeños Héroes",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(top = 24.dp, bottom = 16.dp)
                )

                // Texto de bienvenida centrado
                Text(
                    text = "¡Bienvenido!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = currentUser?.username ?: "Usuario",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Misión
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Misión",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "Brindar atención pediátrica de alta calidad, enfocados en la seguridad de nuestros pacientes, ofreciendo una atención médica integral por el mejor equipo de profesionales que conducen con ética, humanismo y excelencia.",
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Justify
                        )
                    }
                }

                // Visión
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Visión",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "Ser el hospital líder en la atención médica pediátrica, que brinde la mejor experiencia hospitalaria en el departamento, impulsando y mejorando un modelo de asistencial privado.",
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Justify
                        )
                    }
                }

                // Valores
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Valores",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Valor: Respeto
                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
                            Text(
                                text = "✅ Respeto",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Tratamos a cada niño con ética y cuidado que merece",
                                fontSize = 15.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }

                        // Valor: Seguridad
                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "✅ Seguridad",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Los más altos estándares de calidad en atención médica",
                                fontSize = 15.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }


                        //Valor: Compromiso
                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "✅ Compromiso",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Tecnología de punta para mejores diagnósticos y tratamientos",
                                fontSize = 15.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }

                        //Valor Innovación
                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "✅ Innovacion",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Colaboración multidisciplinaria para el bienestar del paciente",
                                fontSize = 15.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(start = 16.dp)

                            )
                        }
                    }
                }

                // Espacio final
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// Componente para el Header del Drawer
@Composable
fun DrawerHeader(currentUser: User?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Pequeños Héroes",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = currentUser?.username ?: "Usuario",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = currentUser?.email ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
    Divider()
}

// Componente para los títulos de sección del Drawer
@Composable
fun DrawerSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 28.dp, top = 16.dp, bottom = 8.dp)
    )
}

