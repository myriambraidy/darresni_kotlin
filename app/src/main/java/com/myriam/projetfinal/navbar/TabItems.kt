package com.myriam.projetfinal.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val color: Color
) {
    object Home : TabItem("Home", "home", Icons.Filled.Home, Color(0xFF1E88E5)) // Blue
    object Profile : TabItem("Profile", "profile", Icons.Filled.Person, Color(0xFFD81B60)) // Pink
    object Settings : TabItem("Settings", "settings", Icons.Filled.Settings, Color(0xFF43A047)) // Green
}