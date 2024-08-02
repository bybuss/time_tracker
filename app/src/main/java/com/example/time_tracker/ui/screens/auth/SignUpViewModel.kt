package com.example.time_tracker.ui.screens.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.data.network.TimeTrackerRepository

import com.stevdzasan.onetap.GoogleUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * @author bybuss
*/

sealed interface SignUpUiState {
    data class Success(val id: Int): SignUpUiState
    data class Error(val exception: String): SignUpUiState
    object Loading: SignUpUiState
}

class SignUpViewModel(
    private val timeTrackerRepository: TimeTrackerRepository
): ViewModel() {
    private var _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Loading)
    var uiState = _uiState.asStateFlow()

    val permissions: Map<String, Boolean> = mapOf("insert" to true, "update" to true, "read" to true)
    val name = "member"

    init {
        viewModelScope.launch{ addRole(name, permissions) }
    }


    suspend fun addRole(name: String, permissions: Map<String, Boolean>) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addRole(name, permissions))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            }

        }
    }
}