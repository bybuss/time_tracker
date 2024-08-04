package com.example.time_tracker.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.time_tracker.ui.AppViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author bybuss
 */

@Composable
fun AddRoleTestScreen() {
    val signUpViewModel: SignUpViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState by signUpViewModel.uiState.collectAsState()
    val permissions: Map<String, Boolean> = mapOf("insert" to true, "update" to true, "read" to true)
    val roleName = "member"
    val coroutineScope = rememberCoroutineScope()
    val firstName = "eblo"
    val lastName = "slona"
    val roleId = 1
    val email = "babakapa729@gmail.com"
    val password = "string"
    val organizationName = "organization"
    val description = "description"


    AddRoleTest(
        uiState = uiState,
//        retryAction = {coroutineScope.launch{signUpViewModel.addRole(
//            name = roleName,
//            permissions = permissions,
//        )}}

//        retryAction = {coroutineScope.launch{signUpViewModel.authUser(
//            email = email,
//            password = password
//        )}}

//        retryAction = {coroutineScope.launch{signUpViewModel.addUser(
//            firstName = firstName,
//            lastName = lastName,
//            roleId = roleId,
//            email = email,
//            password = password
//        )}}

        retryAction = {coroutineScope.launch{signUpViewModel.addOrganization(
            name = organizationName,
            description = description,
        )}}
    )
}

@Composable
fun AddRoleTest(uiState: SignUpUiState, retryAction: () -> Unit) {
    when (uiState) {
        is SignUpUiState.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Loading...")
                    CircularProgressIndicator()
                }
                Button(onClick = { retryAction() }){Text("Retry")}
            }
        }

        is SignUpUiState.Success -> {
            Text(text = "${uiState.id}", modifier = Modifier.fillMaxSize())
        }

        is SignUpUiState.Error -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = { retryAction() }){Text("Retry")}
                Text(text = uiState.exception, modifier = Modifier.fillMaxSize())
            }

        }
    }
}