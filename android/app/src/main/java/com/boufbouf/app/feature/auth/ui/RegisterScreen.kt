package com.boufbouf.app.feature.auth.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.boufbouf.app.core.ui.BoufBoufOrange
import com.boufbouf.app.feature.auth.AuthViewModelFactory
import com.boufbouf.app.feature.auth.RegisterViewModel

@Composable
fun RegisterRoute(
    context: Context,
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
) {
    val viewModel: RegisterViewModel = viewModel(
        factory = AuthViewModelFactory(context)
    )

    RegisterScreen(
        viewModel = viewModel,
        onRegisterSuccess = onRegisterSuccess,
        onBackToLogin = onBackToLogin,
    )
}

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onRegisterSuccess: () -> Unit,
    onBackToLogin: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    var languageMenuExpanded by remember {
        mutableStateOf(false)
    }

    if (uiState.isRegistered) {
        onRegisterSuccess()
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "BOUF-BOUF",
                color = BoufBoufOrange,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Rejoins la communauté",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Crée ton compte et partage ta passion\npour la bonne cuisine.",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(28.dp))

            OutlinedTextField(
                value = uiState.username,
                onValueChange = viewModel::updateUsername,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nom d'utilisateur") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Nom d'utilisateur",
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.phoneNumber,
                onValueChange = viewModel::updatePhoneNumber,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Téléphone (optionnel)") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Téléphone",
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.displayName,
                onValueChange = viewModel::updateDisplayName,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nom affiché") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Nom affiché",
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Mot de passe") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Mot de passe",
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (passwordVisible) {
                                "Masquer le mot de passe"
                            } else {
                                "Afficher le mot de passe"
                            },
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Confirmer le mot de passe") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Confirmation du mot de passe",
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            confirmPasswordVisible = !confirmPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (confirmPasswordVisible) {
                                "Masquer le mot de passe"
                            } else {
                                "Afficher le mot de passe"
                            },
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = uiState.country,
                onValueChange = viewModel::updateCountry,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Pays") },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                enabled = !uiState.isLoading,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = when (uiState.language) {
                        "fr" -> "Français"
                        "en" -> "English"
                        "ar" -> "العربية"
                        "es" -> "Español"
                        else -> uiState.language
                    },
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Langue") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Langue",
                        )
                    },
                    readOnly = true,
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    enabled = !uiState.isLoading,
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable(
                            enabled = !uiState.isLoading,
                            onClick = {
                                languageMenuExpanded = true
                            }
                        )
                )

                DropdownMenu(
                    expanded = languageMenuExpanded,
                    onDismissRequest = {
                        languageMenuExpanded = false
                    },
                    modifier = Modifier.fillMaxWidth(0.88f),
                ) {
                    DropdownMenuItem(
                        text = { Text("🇫🇷 Français") },
                        onClick = {
                            viewModel.updateLanguage("fr")
                            languageMenuExpanded = false
                        },
                    )

                    DropdownMenuItem(
                        text = { Text("🇬🇧 English") },
                        onClick = {
                            viewModel.updateLanguage("en")
                            languageMenuExpanded = false
                        },
                    )

                    DropdownMenuItem(
                        text = { Text("🇲🇦 العربية") },
                        onClick = {
                            viewModel.updateLanguage("ar")
                            languageMenuExpanded = false
                        },
                    )

                    DropdownMenuItem(
                        text = { Text("🇪🇸 Español") },
                        onClick = {
                            viewModel.updateLanguage("es")
                            languageMenuExpanded = false
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            uiState.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = viewModel::register,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !uiState.isLoading,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BoufBoufOrange,
                    contentColor = Color.White,
                ),
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.height(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp,
                    )
                } else {
                    Text(
                        text = "CRÉER MON COMPTE",
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = onBackToLogin,
                enabled = !uiState.isLoading,
            ) {
                Text("J'ai déjà un compte")
            }
        }
    }
}
