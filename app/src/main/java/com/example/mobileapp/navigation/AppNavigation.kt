    package com.example.mobileapp.navigation

    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.interaction.MutableInteractionSource
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.NavigationBar
    import androidx.compose.material3.Scaffold
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.dimensionResource
    import androidx.navigation.NavDestination.Companion.hierarchy
    import androidx.navigation.NavGraph.Companion.findStartDestination
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.currentBackStackEntryAsState
    import androidx.navigation.compose.rememberNavController
    import com.example.mobileapp.R
    import com.example.mobileapp.screens.ProfileScreen
    import com.example.mobileapp.screens.TaskMenu
    import com.example.mobileapp.screens.TasksListScreen
    import com.example.mobileapp.screens.TasksScreen

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.margin_small))
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.margin_large), Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            listOfNavItems.forEach { navItem ->

                                val selected = currentDestination?.hierarchy?.any {
                                    it.route?.startsWith(navItem.route.split("/")[0]) == true
                                } == true

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(if (selected) Color.White else Color.Transparent)
                                        .padding(dimensionResource(R.dimen.margin_medium))
                                        .size(dimensionResource(R.dimen.icon_nav_bar_size))
                                        .clickable(
                                            onClick = {
                                                navController.navigate(navItem.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            },
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() }
                                        )
                                ) {
                                    Icon(
                                        imageVector = navItem.icon,
                                        contentDescription = null,
                                        tint = if (selected) Color.Black else Color.White,
                                        modifier = Modifier.size(dimensionResource(R.dimen.margin_mega_large))
                                    )
                                }
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screens.TasksScreen.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = Screens.ProfileScreen.name) {
                    ProfileScreen()
                }
                composable(route = Screens.TasksScreen.name) {
                    TasksScreen(navController = navController)
                }
                composable(route = "${Screens.TasksScreen.name}/taskMenu") {
                    TaskMenu(navController = navController)
                }
                composable(route = "${Screens.TasksScreen.name}/activity") {
                    TasksListScreen()
                }
            }
        }
    }

