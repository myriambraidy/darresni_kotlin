package com.myriam.projetfinal.screens.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.myriam.projetfinal.data.models.History
import com.myriam.projetfinal.data.models.User
import com.myriam.projetfinal.viewmodels.AppRoutes
import org.koin.androidx.compose.koinViewModel
import com.myriam.projetfinal.screens.profile_screen.ProfileUiState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import com.myriam.projetfinal.components.ButtonVariant
import com.myriam.projetfinal.components.CustomButton


@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = koinViewModel(),
    appNavController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProfileUiState.Success -> {
            val user = state.user
            ProfileContent(
                user = user,
                viewModel = viewModel,
                appNavController = appNavController
            )
        }
        is ProfileUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Could not load profile or user is logged out.")
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomButton(onClick = {
                        appNavController.navigate(AppRoutes.LOGIN) {
                            popUpTo(AppRoutes.MAIN) { inclusive = true }
                            launchSingleTop = true
                        }
                    }) {
                        Text("Go to Login")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileContent(
    user: User,
    viewModel: ProfileScreenViewModel,
    appNavController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF262626)) // Or your desired background
    ) {
        HeaderSectionProfileRedesigned(userProfile = user)

        // Scrollable Content Section
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Ensure LazyColumn takes up remaining space
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center items like Button
        ) {
            item { Spacer(modifier = Modifier.height(24.dp)) } // Space below header

            // Stats Section
            item {
                StatsSectionProfile(userProfile = user)
                Spacer(modifier = Modifier.height(32.dp))
            }

            // User History Section
            item {
//                UserHistorySection(historyItems = user.history) // Get history from User object
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Sign Out Button
            item {
//                Button(
//                    onClick = {
//                        // Trigger logout logic in ViewModel -> Repository
//                        viewModel.performLogout()
//                        // Navigate back to Login screen using the main NavController
//                        appNavController.navigate(AppRoutes.LOGIN) {
//                            // Clear the back stack up to the main authenticated section
//                            popUpTo(AppRoutes.MAIN) { inclusive = true }
//                            // Avoid multiple instances of the login screen
//                            launchSingleTop = true
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth(0.8f) // Make button slightly less wide
//                        .padding(vertical = 16.dp),
//                    // Use error color scheme for logout button
//                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
//                ) {
//                    Text("Sign Out")
//                }
                CustomButton(
                    onClick = {

                        // Trigger logout logic in ViewModel -> Repository
                        viewModel.performLogout()
                        // Navigate back to Login screen using the main NavController
                        appNavController.navigate(AppRoutes.LOGIN) {
                            // Clear the back stack up to the main authenticated section
                            popUpTo(AppRoutes.MAIN) { inclusive = true }
                            // Avoid multiple instances of the login screen
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 16.dp),
                    label = "Sign Out",
                    variant = ButtonVariant.Destructive
                )
            }
            }
        }
    }


@Composable
fun HeaderSectionProfileRedesigned(userProfile: User) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.DarkGray, Color.Gray)
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(Icons.Filled.AccountCircle, contentDescription = "Profile", tint=Color.White, modifier = Modifier.size(80.dp))
            // }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = userProfile.username,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

//@Composable
//fun UserHistorySection(historyItems: List<History>) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//        Text(
//            text = "Recent Activity",
//            style = MaterialTheme.typography.titleMedium, // Adjusted style
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 12.dp)
//        )
//        if (historyItems.isEmpty()) {
//            Text(
//                text="No recent activity found.",
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(vertical = 8.dp)
//            )
//        } else {
//            // Use LazyColumn's items extension for efficiency if list can be long
//            historyItems.forEach { item ->
//                HistoryItemCard(item = item)
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//            // Example using items extension (if historyItems can be large):
//            // items(historyItems, key = { it.id }) { item ->
//            //     HistoryItemCard(item = item)
//            //     Spacer(modifier = Modifier.height(8.dp))
//            // }
//        }
//    }
//}


//@Composable
//fun HistoryItemCard(item: History) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp), // Subtle elevation
//        colors = CardDefaults.cardColors(
//            // Slightly color based on success/failure
//            containerColor = if(item.success) Color(0xFFE8F5E9) else Color(0xFFFFF0F0)
//        )
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(
//                text = item.description,
//                modifier = Modifier.weight(1f).padding(end = 8.dp),
//                style = MaterialTheme.typography.bodyMedium
//            )
//            Text(
//                text = item.date,
//                style = MaterialTheme.typography.bodySmall,
//                color = Color.Gray
//            )
//        }
//    }
//}

@Composable
fun StatsSectionProfile(userProfile: User) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Statistics",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp ,
            modifier = Modifier.padding(bottom=12.dp)
        )
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF363333),
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatItem("Days Active", userProfile.streak.toString())
        }
    }
}

// Helper composable for individual stats
@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = MaterialTheme.typography.headlineSmall)
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

