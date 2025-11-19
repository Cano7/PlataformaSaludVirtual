package com.example.plataformasaludvirtual


import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
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
                                text = "Hospital Pediatrico Especializado",
                                fontSize = 18.sp,
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
                )
            }
        )

//AQUI INICIA INFORMACION DEL INICIO
        { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "Brindar atención pediátrica de alta calidad, enfocados en la " +
                                    "seguridad de nuestros pacientes, ofreciendo una atención médica " +
                                    "integral por el mejor equipo de profesionales que conducen con ética, " +
                                    "humanismo y excelencia.",
                            fontSize = 20.sp,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Unspecified
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
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "Ser el hospital líder en la atención médica pediátrica, " +
                                    "que brinde la mejor experiencia hospitalaria en el departamento, " +
                                    "impulsando y mejorando un modelo de asistencial privado.",
                            fontSize = 20.sp,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.Unspecified
                        )
                    }
                }

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
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Column(modifier = Modifier.padding(bottom = 16.dp)) {
                            Text(
                                text = "✅Respeto",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Tratamos a cada niño con ética y cuidado que merece",
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Unspecified,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }

                        // Valor: Seguridad
                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "✅Seguridad",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Los más altos estándares de calidad en atención médica",
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Unspecified,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }


                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "✅Compromiso",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Tecnología de punta para mejores diagnósticos y tratamientos",
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Unspecified,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }

                        Column(modifier = Modifier.padding(bottom = 8.dp)) {
                            Text(
                                text = "✅Innovacion",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Colaboración multidisciplinaria para el bienestar del paciente",
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Unspecified,
                                modifier = Modifier.padding(start = 16.dp)

                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

//EL INICIO DEL DISEÑO DEL MENU
@Composable
fun DrawerHeader(currentUser: User?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF5268A2))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Pequeños Héroes",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.background,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = currentUser?.username ?: "Salud Virtual",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.background,
            fontSize = 18.sp
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

