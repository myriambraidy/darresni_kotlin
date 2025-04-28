package com.myriam.projetfinal.screens.login_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lint.kotlin.metadata.Visibility
import androidx.navigation.NavController
import com.myriam.projetfinal.R
import com.myriam.projetfinal.viewmodels.AppRoutes
import kotlin.math.log

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
    navController: NavController
) {
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val isLoginEnabled by viewModel.isLoginEnabled.observeAsState(false)
    val loginError by viewModel.loginError.observeAsState("")

    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // App Logo or Title
            val gradientColors = listOf(
                Color(0xFFFC5F5F),
                Color(0xFFDC143C),
                Color(0xFF8B0000)
            )

            Image(
                painter = painterResource(id = R.drawable.darresni),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(100.dp)
            )

            Text(
                text = "Darresni",
                fontSize = 30.sp,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors,
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            // Password Input
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChanged(it) },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )

            // Error Text
            if (loginError.isNotEmpty()) {
                Text(
                    text = loginError,
                    color = Color.Red,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Button(
                onClick = {
                    viewModel.onLoginClicked {
                        Log.d("UserRepository", "Functionnn")
                        // Navigate to main app flow after successful login
//                        navController.navigate(AppRoutes.MAIN) {
//                            popUpTo(AppRoutes.LOGIN) { inclusive = true }
//                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                enabled = isLoginEnabled
            ) {
                Text("Login", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign Up Text - Fixed alignment
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don't have an account?", color = Color.White)
                TextButton(onClick = { navController.navigate(AppRoutes.SIGNUP) }) {
                    Text("Sign Up", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}