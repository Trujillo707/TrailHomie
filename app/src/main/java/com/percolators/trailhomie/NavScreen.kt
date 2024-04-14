package com.percolators.trailhomie


// Basically some enums for the screen destinations
sealed class NavScreen(val route:String) {
    object HomeScreen: NavScreen("home_screen")
    object ReportScreen: NavScreen("report_screen")
}