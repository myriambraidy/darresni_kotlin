package com.myriam.projetfinal.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.myriam.projetfinal.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = viewModel()
) {
    val settingsUiState by viewModel.settingsUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val showResetDialog = remember { mutableStateOf(false) }
    val showSignOutDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Learning goals section
            SettingsSection(title = "Learning Goals") {
                // Daily goal slider
                Text("Daily Learning Goal", style = MaterialTheme.typography.bodyLarge)
                Text(
                    "${settingsUiState.dailyGoal} minutes per day",
                    style = MaterialTheme.typography.bodyMedium
                )
                Slider(
                    value = settingsUiState.dailyGoal.toFloat(),
                    onValueChange = { viewModel.updateDailyGoal(it.toInt()) },
                    valueRange = 5f..60f,
                    steps = 11
                )

                // Primary language selection
                OutlinedCard(
                    onClick = { /* Open language selection dialog */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItem(
                        headlineContent = { Text("Primary Programming Language") },
                        supportingContent = {
                            Text(
                                settingsUiState.codeLanguages.getOrNull(
                                    settingsUiState.selectedLanguageIndex
                                ) ?: "Not selected"
                            )
                        },
                        trailingContent = {
                            Icon(
                                Icons.Default.KeyboardArrowRight,
                                contentDescription = "Select"
                            )
                        }
                    )
                }

                // Difficulty level
                OutlinedCard(
                    onClick = { /* Open difficulty selection dialog */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItem(
                        headlineContent = { Text("Difficulty Level") },
                        supportingContent = { Text(settingsUiState.difficultyLevel.name.lowercase().capitalize()) },
                        trailingContent = {
                            Icon(
                                Icons.Default.KeyboardArrowRight,
                                contentDescription = "Select"
                            )
                        }
                    )
                }
            }

            // App preferences section
            SettingsSection(title = "App Preferences") {
                // Sound toggle
                ListItem(
                    headlineContent = { Text("Sound Effects") },
                    trailingContent = {
                        Switch(
                            checked = settingsUiState.soundEnabled,
                            onCheckedChange = { viewModel.toggleSound(it) }
                        )
                    },
                    leadingContent = {
                        Icon(
                            Icons.Default.Phone,
                            contentDescription = null
                        )
                    }
                )

                // Notifications toggle
                ListItem(
                    headlineContent = { Text("Notifications") },
                    trailingContent = {
                        Switch(
                            checked = settingsUiState.notificationsEnabled,
                            onCheckedChange = { viewModel.toggleNotifications(it) }
                        )
                    },
                    leadingContent = {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null
                        )
                    }
                )

                // Dark mode toggle
                ListItem(
                    headlineContent = { Text("Dark Mode") },
                    trailingContent = {
                        Switch(
                            checked = settingsUiState.darkModeEnabled,
                            onCheckedChange = { viewModel.toggleDarkMode(it) }
                        )
                    },
                    leadingContent = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null
                        )
                    }
                )
            }

            // Account actions section
            SettingsSection(title = "Account") {
                // Reset progress
                OutlinedCard(
                    onClick = { showResetDialog.value = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItem(
                        headlineContent = { Text("Reset Progress") },
                        supportingContent = { Text("This will reset all your learning progress") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    )
                }

                // Sign out
                OutlinedCard(
                    onClick = { showSignOutDialog.value = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItem(
                        headlineContent = { Text("Sign Out") },
                        leadingContent = {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = null
                            )
                        }
                    )
                }
            }

            // App info section
            SettingsSection(title = "About") {
                ListItem(
                    headlineContent = { Text("Version") },
                    supportingContent = { Text("1.0.0") }
                )

                ListItem(
                    headlineContent = { Text("Terms of Service") },
                    trailingContent = {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Open"
                        )
                    }
                )

                ListItem(
                    headlineContent = { Text("Privacy Policy") },
                    trailingContent = {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Open"
                        )
                    }
                )
            }
        }
    }

    // Reset progress confirmation dialog
    if (showResetDialog.value) {
        AlertDialog(
            onDismissRequest = { showResetDialog.value = false },
            title = { Text("Reset Progress") },
            text = { Text("Are you sure you want to reset all your learning progress? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetProgress()
                        showResetDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showResetDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Sign out confirmation dialog
    if (showSignOutDialog.value) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog.value = false },
            title = { Text("Sign Out") },
            text = { Text("Are you sure you want to sign out?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.signOut()
                        showSignOutDialog.value = false
                        // Navigate to login screen
                        // navController.navigate("login") { popUpTo(0) }
                    }
                ) {
                    Text("Sign Out")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showSignOutDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

