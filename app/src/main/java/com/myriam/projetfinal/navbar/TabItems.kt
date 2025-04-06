package com.myriam.projetfinal.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.myriam.projetfinal.ui.theme.PrimaryColorGreen

sealed class TabItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val color: Color
) {
    object Home : TabItem("Home", "home", Icons.Filled.Home, PrimaryColorGreen)
    object Exercises : TabItem("Exercises", "Exercises", Icons.Filled.Menu, PrimaryColorGreen)
    object Profile : TabItem("Profile", "profile", Icons.Filled.Person, PrimaryColorGreen)
}