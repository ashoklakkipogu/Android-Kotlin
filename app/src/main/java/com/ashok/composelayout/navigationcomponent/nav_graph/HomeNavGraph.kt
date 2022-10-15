package com.ashok.composelayout.navigationcomponent.nav_graph

import android.util.Log
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ashok.composelayout.navigationcomponent.*

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(DETAIL_ARGUMENT_KEY2) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            Log.d("Arg", it.arguments?.getInt(DETAIL_ARGUMENT_KEY).toString())
            Log.d("Arg", it.arguments?.getString(DETAIL_ARGUMENT_KEY2).toString())
            DetailsScreen(navController)
        }
    }
}