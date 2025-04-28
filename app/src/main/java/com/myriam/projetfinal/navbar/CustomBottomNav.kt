package com.myriam.projetfinal.navbar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun CustomBottomNav(navController: NavController, tabs: List<TabItem>) {
    var selectedTab by remember { mutableStateOf(tabs[0]) }
    val tabWidths = remember { mutableStateListOf<Float>() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFF0505))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEachIndexed { index, tab ->
                val isSelected = selectedTab == tab

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .onGloballyPositioned { coordinates ->
                            if (tabWidths.size < tabs.size) {
                                coroutineScope.launch {
                                    tabWidths.add(index, coordinates.size.width.toFloat())
                                }
                            }
                        }
                        .clickable {
                            selectedTab = tab
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
//                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        tab.icon,
                        contentDescription = tab.title,
                        tint = if (isSelected) tab.color else Color.Gray,
                        modifier = Modifier.size( 28.dp )
                    )
                    Text(
                        tab.title,
                        color = if (isSelected) tab.color else Color.Gray,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}