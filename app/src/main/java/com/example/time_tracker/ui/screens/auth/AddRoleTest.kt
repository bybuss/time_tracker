package com.example.time_tracker.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
    val name = "member"
    val coroutineScope = rememberCoroutineScope()

    AddRoleTest(
        uiState = uiState,
        retryAction = {coroutineScope.launch{ signUpViewModel.addRole(name, permissions) }}
    )
}

@Composable
fun AddRoleTest(uiState: SignUpUiState, retryAction: () -> Unit) {
    when (uiState) {
        is SignUpUiState.Loading -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Loading...", modifier = Modifier.fillMaxHeight())
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.fillMaxHeight())
                }
                Button(onClick = { retryAction() }){Text("Retry")}
            }
        }

        is SignUpUiState.Success -> {
            Text(text = "Success: ${uiState.id}", modifier = Modifier.fillMaxSize())
        }

        is SignUpUiState.Error -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Error: ${uiState.exception}")
                Button(onClick = { retryAction() }){Text("Retry")}
            }


        }
    }
}

