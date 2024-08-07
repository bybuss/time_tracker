package com.example.time_tracker.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.data.network.api.TimeTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.UUID

/**
 * @author bybuss
*/

sealed interface SignUpUiState {
    data class Success(val data: Any?): SignUpUiState
    data class Error(val exception: String): SignUpUiState
    object Loading: SignUpUiState
}

class SignUpViewModel(
    private val timeTrackerRepository: TimeTrackerRepository
): ViewModel() {
    private var _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Loading)
    var uiState = _uiState.asStateFlow()

    suspend fun addRole(name: String, permissions: Map<String, Boolean>) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addRole(
                    name,
                    permissions
                ))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun authUser(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.authUser(
                    email,
                    password
                ))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addUser(
                    firstName,
                    lastName,
                    roleId,
                    email,
                    password
                ))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addOrganization(name: String, description: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addOrganization(
                    name,
                    description
                ))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun refreshToken() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.refreshToken())
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addProject(name: String, organizationId: Int, description: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addProject(
                    name,
                    organizationId,
                    description
                ))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addTask(
        name: String,
        description: String,
        isDone: Boolean,
        assignerId: UUID,
        color: String,
        duration: Int,
        endDate: String?,
        difficulty: String,
        projectId: Int,
        groupId: Int?,
        assignees: List<Map<String, Any>>
    ) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addTask(
                    name,
                    description,
                    isDone,
                    assignerId,
                    color,
                    duration,
                    endDate,
                    difficulty,
                    projectId,
                    groupId,
                    assignees
                ))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getFullTaskById(assignerId: UUID) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.getFullTaskById(assignerId))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getSimpleTaskByAssignerId(assignerId: UUID) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.getSimpleTaskByAssignerId(assignerId))
            } catch (e: IOException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: ApolloException) {
                SignUpUiState.Error(e.message.toString())
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }
}