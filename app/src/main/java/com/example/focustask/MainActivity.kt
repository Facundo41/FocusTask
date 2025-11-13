package com.example.focustask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focustask.ui.theme.FocusTaskTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost

object Screen {
    const val HOME = "MainActivity"
    const val TASKS = "tasks_screen"
    const val SUBJECTS = "subjects"
    const val POMODORO = "pomodoro"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusTaskTheme {
                AppScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent() {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))

            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        HeaderSection()

        Spacer(modifier = Modifier.height(24.dp))


        StatisticsGrid()

        Spacer(modifier = Modifier.height(24.dp))


        ExpandableSection(title = "Progreso General")

        Spacer(modifier = Modifier.height(12.dp))

        ExpandableSection(title = "Materias Activas")
    }
}

@Composable
fun HeaderSection() {
    Column {
        Text(
            text = "¡Hola (usuario)!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Acá está tu resumen académico",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun StatisticsGrid() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatisticCard(
                title = "Tareas\nCompletadas",
                value = "0",
                icon = Icons.Default.Check,
                iconColor = Color(0xFF4CAF50),
                backgroundColor = Color(0xFFE8F5E8),
                modifier = Modifier.weight(1f)
            )

            StatisticCard(
                title = "Pendientes",
                value = "0",
                icon = Icons.Default.List,
                iconColor = Color(0xFFF44336),
                backgroundColor = Color(0xFFFFE8E8),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatisticCard(
                title = "Vencidas",
                value = "0",
                icon = Icons.Default.Warning,
                iconColor = Color(0xFFFF9800),
                backgroundColor = Color(0xFFFFF3E0),
                modifier = Modifier.weight(1f)
            )

            StatisticCard(
                title = "Completado",
                value = "0%",
                icon = Icons.Default.Star,
                iconColor = Color(0xFF2196F3),
                backgroundColor = Color(0xFFE3F2FD),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatisticCard(
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = value,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Composable
fun ExpandableSection(title: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expandir",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, currentRoute)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = Screen.HOME
            ) {
                composable(Screen.HOME) { HomeScreenContent() }
                composable(Screen.TASKS) { TasksScreen() }
                composable(Screen.SUBJECTS) { SubjectsScreen() }
                composable(Screen.POMODORO) { PomodoroScreen() }
            }
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val navItems = listOf(
        Pair(Screen.HOME, Icons.Default.Home to "Inicio"),
        Pair(Screen.TASKS, Icons.Default.List to "Tareas"),
        Pair(Screen.SUBJECTS, Icons.Default.Star to "Materias"),
        Pair(Screen.POMODORO, Icons.Default.Person to "Pomodoro")
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        navItems.forEach { (route, content) ->
            val (icon, label) = content
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = currentRoute == route,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun TasksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¡Pantalla de Tareas!", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text("Aquí verás la lista de tareas.", fontSize = 18.sp, color = Color.Gray)
    }
}

@Composable
fun SubjectsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¡Pantalla de Materias!", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text("Aquí verás las diferentes materias.", fontSize = 18.sp, color = Color.Gray)
    }
}

@Composable
fun PomodoroScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("¡Pantalla del Pomodoro!", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text("Aquí verás el temporizador que ayudara a aplicar el metodo pomodoro.", fontSize = 18.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FocusTaskTheme {
        Greeting("Android")
    }
}