package com.lakki.ashok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lakki.ashok.components.AppBar
import com.lakki.ashok.components.DrawerBody
import com.lakki.ashok.components.DrawerHeader
import com.lakki.ashok.components.MenuItem
import com.lakki.ashok.nav.HelpScreen
import com.lakki.ashok.nav.HomeScreen
import com.lakki.ashok.nav.Screens
import com.lakki.ashok.nav.SettingsScreen
import com.lakki.ashok.ui.theme.ashokTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ashokTheme {
                val menuItems = listOf(
                    MenuItem(
                        id = "home",
                        name = "Home",
                        icon = Icons.Default.Home,
                        contentDesc = "Home"
                    ),
                    MenuItem(
                        id = "settings",
                        name = "Settings",
                        icon = Icons.Default.Settings,
                        contentDesc = "Settings"
                    ),
                    MenuItem(
                        id = "help",
                        name = "Help",
                        icon = Icons.Default.Info,
                        contentDesc = "Help"
                    )
                )
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navHostController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            })
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = menuItems
                        ) {
                            println("Clicked on ${it.name}")
                            navHostController.navigate(it.name) {
                                navHostController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true

                                }

                            }
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    },
                    bottomBar = {
                        BottomBar(navController = navHostController)
                    },
                    content = {
                        Navigation(navHostController)

                    }
                )
            }
        }
        //startActivity(Intent(this, LoginActivity::class.java))
    }
}

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.router
    ) {
        composable(Screens.Home.router) {
            HomeScreen()
        }
        composable(Screens.Settings.router) {
            SettingsScreen()
        }
        composable(Screens.Help.router) {
            HelpScreen()
        }
    }

}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Screens.Home,
        Screens.Settings,
        Screens.Help,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.router
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.router) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ashokTheme {

    }
}