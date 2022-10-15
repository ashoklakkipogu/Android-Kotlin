package com.ashok.composelayout.navigationcomponent.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ashok.composelayout.navigationcomponent.AUTH_GRAPH_ROUTE
import com.ashok.composelayout.navigationcomponent.LoginScreen
import com.ashok.composelayout.navigationcomponent.Screen
import com.ashok.composelayout.navigationcomponent.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.SignUp.route
        ) {
            SignupScreen(navController = navController)
        }
    }
}