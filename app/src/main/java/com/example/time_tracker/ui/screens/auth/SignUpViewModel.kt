package com.example.time_tracker.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time_tracker.data.network.api.TimeTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
    var token: String? = null
        private set

    fun setToken(responseToken: String) {
        token = responseToken
    }

    suspend fun addRole(name: String, permissions: Map<String, Boolean>) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.addRole(
                    name,
                    permissions
                ))
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
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addTask(
        name: String,
        description: String,
        isDone: Boolean,
        assignerId: String,
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
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getFullTasksByAssignerId(assignerId: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.getFullTasksByAssignerId(assignerId))
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getSimpleTasksByAssignerId(assignerId: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.getSimpleTasksByAssignerId(assignerId))
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun requestChangePassword(
        id: String,
        firstName: String,
        lastName: String,
        email: String
    ) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                SignUpUiState.Success(timeTrackerRepository.requestChangePassword(
                    id,
                    firstName,
                    lastName,
                    email
                ))
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun changePassword(newPassword: String) {
        token?.let {
            viewModelScope.launch {
                _uiState.value = SignUpUiState.Loading
                _uiState.value = try {
                    SignUpUiState.Success(timeTrackerRepository.changePassword(newPassword, it))
                } catch (e: Exception) {
                    SignUpUiState.Error(e.message.toString())
                }
            }
        } ?: SignUpUiState.Error("Token is null")
    }
}