package com.example.easyhomeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.easyhomeapp.navigation.BottomNavigationItem
import com.example.easyhomeapp.navigation.Screens
import com.example.easyhomeapp.navigation.ScreensLabel
import com.example.easyhomeapp.screens.DetailDeveloperScreen
import com.example.easyhomeapp.screens.HomeScreen
import com.example.easyhomeapp.screens.ProfileScreen
import com.example.easyhomeapp.screens.SearchScreen


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun EasyHomeApp() {
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf(ScreensLabel.Home.route)}

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = currentScreen, fontWeight = FontWeight.Black) },
                navigationIcon = {
                    if (currentScreen == ScreensLabel.DetailDeveloper.route) {
                        IconButton(
                            onClick = { navController.navigateUp() }
                        ) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Home.route) {
                currentScreen = ScreensLabel.Home.route
                HomeScreen(
                    navController
                )
            }
            composable(Screens.Katalog.route) {
                currentScreen = ScreensLabel.Katalog.route
                SearchScreen(
                    navController
                )
            }
            composable(Screens.Profile.route) {
                currentScreen = ScreensLabel.Profile.route
                ProfileScreen(
                    navController
                )
            }
            composable(
                Screens.DetailDeveloper.route + "/{DeveloperId}",
                arguments = listOf(navArgument("DeveloperId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                currentScreen = ScreensLabel.DetailDeveloper.route
                DetailDeveloperScreen(
                    navController = navController,
                    developerId  = navBackStackEntry.arguments?.getInt("DeveloperId")
                )
            }
        }
    }
}



