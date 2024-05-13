package com.example.easyhomeapp.navigation

sealed class Screens (val route: String){
    object Home : Screens("home_route")
    object Katalog : Screens("katalog_route")
    object Profile : Screens("profile_route")
    object DetailDeveloper: Screens("detail_developer")
}
sealed class ScreensLabel (val label: String){
    object Home : Screens("Home")
    object Katalog : Screens("Katalog")
    object Profile : Screens("Profile")
    object DetailDeveloper: Screens("Detail Developer")
}