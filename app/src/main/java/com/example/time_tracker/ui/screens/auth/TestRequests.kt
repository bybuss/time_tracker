package com.example.time_tracker.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.time_tracker.ui.AppViewModelProvider
import kotlinx.coroutines.launch
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties
import java.util.UUID

/**
 * @author bybuss
 */

data class ButtonAction(val label: String, val action: () -> Unit)

@Composable
fun TestButtonsScreen() {
    val signUpViewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by signUpViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }

    val buttons = listOf(
        ButtonAction("Add Role") {
            coroutineScope.launch {
                signUpViewModel.addRole(
                    name = "member",
                    permissions = mapOf("insert" to true, "update" to true, "read" to true)
                )
            }
            showDialog.value = true
        },
        ButtonAction("Add User") {
            coroutineScope.launch {
                signUpViewModel.addUser(
                    firstName = "eblo",
                    lastName = "slona",
                    roleId = 1,
                    email = "babakapa729@gmail.com",
                    password = "string"
                )
            }
            showDialog.value = true
        },
        ButtonAction("Add Organization") {
            coroutineScope.launch {
                signUpViewModel.addOrganization(
                    name = "organization",
                    description = "description"
                )
            }
            showDialog.value = true
        },
        ButtonAction("Auth User") {
            coroutineScope.launch {
                signUpViewModel.authUser(
                    email = "babakapa729@gmail.com",
                    password = "string"
                )
            }
            showDialog.value = true
        },
        ButtonAction("Refresh Token") {
            coroutineScope.launch {
                signUpViewModel.refreshToken()
            }
            showDialog.value = true
        },
        ButtonAction("addProject") {
            coroutineScope.launch {
                signUpViewModel.addProject(
                    name = "project",
                    description = "description",
                    organizationId = 1
                )
            }
            showDialog.value = true
        },
        ButtonAction("Get Full Tasks By Assigner Id") {
            coroutineScope.launch {
                signUpViewModel.getFullTasksByAssignerId(UUID.fromString("96b3d5fd-523c-45e6-9002-66d50902bfc4"))
            }
            showDialog.value = true
        },
        ButtonAction("Get Simple Tasks By Assigner Id") {
            coroutineScope.launch {
                signUpViewModel.getSimpleTasksByAssignerId(UUID.fromString("96b3d5fd-523c-45e6-9002-66d50902bfc4"))
            }
            showDialog.value = true
        },
        ButtonAction("Add Task") {
            coroutineScope.launch {
                signUpViewModel.addTask(
                    name = "zalupa2",
                    description = "slona",
                    isDone = false,
                    assignerId = UUID.fromString("96b3d5fd-523c-45e6-9002-66d50902bfc4"),
                    color = "#FF1A1A",
                    duration = 720012,
                    endDate = null,
                    difficulty = "hard",
                    projectId = 1,
                    groupId = null,
                    assignees = listOf(
                        mapOf(
                            "id" to UUID.fromString("96b3d5fd-523c-45e6-9002-66d50902bfc4"),
                            "organizationId" to 1
                        )
                    )
                )
            }
            showDialog.value = true
        }
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        buttons.forEach { buttonAction ->
            Button(onClick = buttonAction.action) {
                Text(text = buttonAction.label)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showDialog.value) {
            HandleUiState(uiState) { showDialog.value = false }
        }
    }
}

@Composable
fun HandleUiState(uiState: SignUpUiState, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        properties = DialogProperties(usePlatformDefaultWidth = false),
        text = {
            Box(
                modifier = Modifier
                    .fillMaxSize(0.56f)
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }

                when (uiState) {
                    is SignUpUiState.Loading -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Loading...")
                            CircularProgressIndicator(modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    is SignUpUiState.Success -> {
                        Text(text = "Success: ${uiState.data}")
                    }

                    is SignUpUiState.Error -> {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Error: ${uiState.exception}")
                            Button(onClick = onDismiss) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }
        }
    )
}