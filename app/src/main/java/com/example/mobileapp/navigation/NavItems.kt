package com.example.mobileapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf(
    NavItem(Icons.Default.Person, route = Screens.ProfileScreen.name),
    NavItem(Icons.Filled.DateRange, route = Screens.TasksScreen.name)
)
