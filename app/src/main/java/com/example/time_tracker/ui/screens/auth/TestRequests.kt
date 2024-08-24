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
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.DialogProperties
import com.example.time_tracker.domain.model.AdminRole
import com.example.time_tracker.domain.model.ButtonAction

/**
 * @author bybuss
 */
@Composable
fun TestButtonsScreen() {
    val signUpViewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by signUpViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    val buttons = listOf(
        ButtonAction("Add Role") {
            coroutineScope.launch {
                signUpViewModel.addRole(
                    name = AdminRole.name,
                    permissions = AdminRole.permissions
                )
            }
            showDialog = true
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
            showDialog = true
        },
        ButtonAction("Add Organization") {
            coroutineScope.launch {
                signUpViewModel.addOrganization(
                    name = "organization",
                    description = "description"
                )
            }
            showDialog = true
        },
        ButtonAction("Auth User") {
            coroutineScope.launch {
                signUpViewModel.authUser(
                    email = "babakapa729@gmail.com",
                    password = "string"
                )
            }
            showDialog = true
        },
        ButtonAction("Refresh Token") {
            coroutineScope.launch {
                signUpViewModel.refreshToken()
            }
            showDialog = true
        },
        ButtonAction("addProject") {
            coroutineScope.launch {
                signUpViewModel.addProject(
                    name = "project",
                    description = "description",
                    organizationId = 1
                )
            }
            showDialog = true
        },
        ButtonAction("Get Full Tasks By Assigner Id") {
            coroutineScope.launch {
                signUpViewModel.getFullTasksByAssignerId("96b3d5fd-523c-45e6-9002-66d50902bfc4")
            }
            showDialog = true
        },
        ButtonAction("Get Full Tasks By Id") {
            coroutineScope.launch {
                signUpViewModel.getFullTasksById(20)
            }
            showDialog = true
        },
        ButtonAction("Get Simple Tasks By Assigner Id") {
            coroutineScope.launch {
                signUpViewModel.getSimpleTasksByAssignerId("96b3d5fd-523c-45e6-9002-66d50902bfc4")
            }
            showDialog = true
        },
        ButtonAction("Get Simple Tasks By Id") {
            coroutineScope.launch {
                signUpViewModel.getSimpleTasksById(20)
            }
            showDialog = true
        },

        ButtonAction("Add Task") {
            coroutineScope.launch {
                signUpViewModel.addTask(
                    name = "zalupa",
                    description = "slona",
                    isDone = false,
                    assignerId = "96b3d5fd-523c-45e6-9002-66d50902bfc4",
                    color = "#FF1A1A",
                    duration = 720012,
                    endDate = null,
                    difficulty = "hard",
                    projectId = 1,
                    groupId = null,
                    assignees = listOf(
                        mapOf(
                            "id" to "96b3d5fd-523c-45e6-9002-66d50902bfc4",
                            "organizationId" to 1
                        )
                    )
                )
            }
            showDialog = true
        },
        ButtonAction("Request Change Password") {
            coroutineScope.launch {
                signUpViewModel.requestChangePassword(
                    id = "96b3d5fd-523c-45e6-9002-66d50902bfc4",
                    firstName = "eblo",
                    lastName ="slona",
                    email = "babakapa729@gmail.com",
                )
            }
            showDialog = true
        },
        ButtonAction("Change Password") {
            coroutineScope.launch {
                signUpViewModel.changePassword(
                    newPassword = "string3",
                )
            }
            showDialog = true
        },
        ButtonAction("Get All Tasks From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllTasksFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Roles From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllRolesFromRoom()
            }
            showDialog = true
        }
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        buttons.forEach { buttonAction ->
            Button(onClick = buttonAction.action) {
                Text(text = buttonAction.label)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (showDialog) {
            HandleUiState(uiState) { showDialog = false }
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