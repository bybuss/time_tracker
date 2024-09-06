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
import kotlinx.coroutines.Dispatchers

/**
 * @author bybuss
 */
@Composable
fun TestButtonsScreen() {
    val signUpViewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by signUpViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope { Dispatchers.IO }
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
        ButtonAction("Add Organization") {
            coroutineScope.launch {
                signUpViewModel.addOrganization(
                    name = "organization",
                    description = "description"
                )
            }
            showDialog = true
        },
        ButtonAction("addProject") {
            coroutineScope.launch {
                signUpViewModel.addProject(
                    name = "project",
                    description = "description",
                    organizationId = 14
                )
            }
            showDialog = true
        },
        ButtonAction("Add User") {
            coroutineScope.launch {
                signUpViewModel.addUser(
                    firstName = "eblo",
                    lastName = "slona",
                    roleId = 17,
                    email = "babakapa729@gmail.com",
                    password = "string"
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
        ButtonAction("Add Task") {
            coroutineScope.launch {
                signUpViewModel.addTask(
                    name = "zalupa",
                    description = "slona",
                    isDone = false,
                    assignerId = "799413a7-b9c0-4d5a-9366-935442838caa",
                    color = "#FF1A1A",
                    duration = 720012,
                    endDate = null,
                    difficulty = "hard",
                    projectId = 6,
                    groupId = null,
                    assignees = listOf(
                        mapOf(
                            "id" to "799413a7-b9c0-4d5a-9366-935442838caa",
                            "organizationId" to 1
                        )
                    )
                )
            }
            showDialog = true
        },
        ButtonAction("Get All Full Tasks By Assigner Id") {
            coroutineScope.launch {
                signUpViewModel.getAllFullTasksByAssignerId("799413a7-b9c0-4d5a-9366-935442838caa")
            }
            showDialog = true
        },
        ButtonAction("Get Full Task By Id") {
            coroutineScope.launch {
                signUpViewModel.getFullTaskById(20)
            }
            showDialog = true
        },
        ButtonAction("Get All Simple Tasks By Assigner Id") {
            coroutineScope.launch {
                signUpViewModel.getAllSimpleTasksByAssignerId("799413a7-b9c0-4d5a-9366-935442838caa")
            }
            showDialog = true
        },
        ButtonAction("Get Simple Task By Id") {
            coroutineScope.launch {
                signUpViewModel.getSimpleTaskById(20)
            }
            showDialog = true
        },
        ButtonAction("Request Change Password") {
            coroutineScope.launch {
                signUpViewModel.requestChangePassword(
                    id = "799413a7-b9c0-4d5a-9366-935442838caa",
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
        },
        ButtonAction("Get All Organizations From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllOrganizationsFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All ProjectsFrom Room") {
            coroutineScope.launch {
                signUpViewModel.getAllProjectsFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Users From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllUsersFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Tasks With Users From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllTasksWithUsersFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Users With Tasks From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllUsersWithTasksFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Users With Organizations From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllUsersWithOrganizationsFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Organizations With Users From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllOrganizationsWithUsersFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All User-Organization Links From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllUserOrgFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All User-Task Links From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllUserTaskFromRoom()
            }
            showDialog = true
        },
        ButtonAction("Get All Groups With Tasks From Room") {
            coroutineScope.launch {
                signUpViewModel.getAllGroupsWithTasksFromRoom()
            }
            showDialog = true
        },
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