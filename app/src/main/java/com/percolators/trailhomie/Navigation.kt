package com.percolators.trailhomie

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.HomeScreen.route){
        composable(route = NavScreen.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = NavScreen.ReportScreen.route + "/{theTrail}/{trailCondition}",
            arguments = listOf(navArgument("theTrail"){
                type = NavType.StringType
            }, navArgument("trailCondition"){
                type = NavType.LongType
            })){ backStackEntry->
            backStackEntry.arguments?.getString("theTrail")
                ?.let { backStackEntry.arguments?.getLong("trailCondition")
                    ?.let { it1 -> ReportScreen(trailToReport = it, trailCondition = it1, navController) } }
        }
    }
}