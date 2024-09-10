package com.example.time_tracker.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time_tracker.domain.useCase.graphQL.AddOrganizationUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddProjectUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddRoleUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddTaskUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddUserUseCase
import com.example.time_tracker.domain.useCase.graphQL.AuthUserUseCase
import com.example.time_tracker.domain.useCase.graphQL.ChangePasswordUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetAllFullTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetAllSimpleTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.room.GetAllTasksUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetFullTaskByIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetSimpleTaskByIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.RefreshTokenUseCase
import com.example.time_tracker.domain.useCase.graphQL.RequestChangePasswordUseCase
import com.example.time_tracker.domain.useCase.room.GetAllGroupsWithTasksUseCase
import com.example.time_tracker.domain.useCase.room.GetAllOrganizationsUseCase
import com.example.time_tracker.domain.useCase.room.GetAllOrganizationsWithUsersUseCase
import com.example.time_tracker.domain.useCase.room.GetAllProjectsUseCase
import com.example.time_tracker.domain.useCase.room.GetAllRolesUseCase
import com.example.time_tracker.domain.useCase.room.GetAllTasksWithUsersUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUserOrgUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUserTaskUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersWithOrganizationsUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersWithTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

/**
 * @author bybuss
*/

sealed interface SignUpUiState {
    data class Success(val data: Any?): SignUpUiState
    data class Error(val exception: String): SignUpUiState
    object Loading: SignUpUiState
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val addRoleUseCase: AddRoleUseCase,
    private val addOrganizationUseCase: AddOrganizationUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val authUserUseCase: AuthUserUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val addProjectUseCase: AddProjectUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val getAllFullTasksByAssignerIdUseCase: GetAllFullTasksByAssignerIdUseCase,
    private val getFullTaskByIdUseCase: GetFullTaskByIdUseCase,
    private val getAllSimpleTasksByAssignerIdUseCase: GetAllSimpleTasksByAssignerIdUseCase,
    private val getSimpleTaskByIdUseCase: GetSimpleTaskByIdUseCase,
    private val requestChangePasswordUseCase: RequestChangePasswordUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,

    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getAllRolesUseCase: GetAllRolesUseCase,
    private val getAllOrganizationsUseCase: GetAllOrganizationsUseCase,
    private val getAllProjectsUseCase: GetAllProjectsUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getAllTasksWithUsersUseCase: GetAllTasksWithUsersUseCase,
    private val getAllUsersWithTasksUseCase: GetAllUsersWithTasksUseCase,
    private val getAllUsersWithOrganizationsUseCase: GetAllUsersWithOrganizationsUseCase,
    private val getAllOrganizationsWithUsersUseCase: GetAllOrganizationsWithUsersUseCase,
    private val getAllUserOrgUseCase: GetAllUserOrgUseCase,
    private val getAllUserTaskUseCase: GetAllUserTaskUseCase,
    private val getAllGroupsWithTasksUseCase: GetAllGroupsWithTasksUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow<SignUpUiState>(
        SignUpUiState.Loading
    )
    var uiState = _uiState.asStateFlow()
    var token: String? = null
        private set

    companion object {
        const val TIMEOUT_MILLIS: Long = 5_000L
    }

    fun setToken(responseToken: String) {
        token = responseToken
    }

    suspend fun addRole(
        name: String,
        permissions: Map<String, Map<String, Boolean>>
    ) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        addRoleUseCase.addRole(name, permissions)
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addOrganization(
        name: String,
        description: String
    ) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        addOrganizationUseCase.addOrganization(
                            name,
                            description
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
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
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        addUserUseCase.addUser(
                            firstName,
                            lastName,
                            roleId,
                            email,
                            password
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun authUser(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(authUserUseCase.authUser(
                        email,
                        password
                    ))
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun refreshToken() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        refreshTokenUseCase.refreshToken()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addProject(
        name: String,
        organizationId: Int,
        description: String
    ) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        addProjectUseCase.addProject(
                            name,
                            organizationId,
                            description
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
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
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        addTaskUseCase.addTask(
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
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getAllFullTasksByAssignerId(assignerId: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllFullTasksByAssignerIdUseCase
                            .getAllFullTasksByAssignerId(assignerId)
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getFullTaskById(id: Int) {
        viewModelScope.launch{
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(getFullTaskByIdUseCase
                        .getFullTaskById(id)
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getAllSimpleTasksByAssignerId(assignerId: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllSimpleTasksByAssignerIdUseCase
                            .getAllSimpleTasksByAssignerId(assignerId)
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun getSimpleTaskById(id: Int) {
        viewModelScope.launch{
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getSimpleTaskByIdUseCase.getSimpleTaskById(id)
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
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
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        requestChangePasswordUseCase
                            .requestChangePassword(
                                id,
                                firstName,
                                lastName,
                                email
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
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
                    withTimeout(TIMEOUT_MILLIS) {
                        SignUpUiState.Success(
                            changePasswordUseCase.changePassword(
                                newPassword, it
                            )
                        )
                    }
                } catch (e: TimeoutCancellationException) {
                    SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
                } catch (e: Exception) {
                    SignUpUiState.Error(e.message.toString())
                }
            }
        } ?: SignUpUiState.Error("Попробуйте снова перейти по ссылке в письме на вашей почте!")
    }

    fun getAllTasksFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllTasksUseCase.getAllTasks().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllRolesFromRoom() {
         viewModelScope.launch {
             _uiState.value = SignUpUiState.Loading
             _uiState.value = try {
                 withTimeout(TIMEOUT_MILLIS) {
                     SignUpUiState.Success(
                         getAllRolesUseCase.getAllRoles().first()
                     )
                 }
             } catch (e: TimeoutCancellationException) {
                 SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
             } catch (e: Exception) {
                 SignUpUiState.Error(e.message.toString())
             }
         }
    }

    fun getAllOrganizationsFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllOrganizationsUseCase
                            .getAllOrganizations().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllProjectsFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllProjectsUseCase.getAllProjects().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllUsersFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllUsersUseCase.getAllUsers().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllTasksWithUsersFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllTasksWithUsersUseCase
                            .getAllTasksWithUsers().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllUsersWithTasksFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllUsersWithTasksUseCase
                            .getAllUsersWithTasks().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllUsersWithOrganizationsFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllUsersWithOrganizationsUseCase
                            .getAllUsersWithOrganizations().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllOrganizationsWithUsersFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllOrganizationsWithUsersUseCase
                            .getAllOrganizationsWithUsers().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllUserOrgFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
               withTimeout(TIMEOUT_MILLIS) {
                   SignUpUiState.Success(getAllUserOrgUseCase
                       .getAllUserOrg().first())
               }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    fun getAllUserTaskFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
               withTimeout(TIMEOUT_MILLIS) {
                   SignUpUiState.Success(getAllUserTaskUseCase
                       .getAllUserTask().first())
               }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }
    fun getAllGroupsWithTasksFromRoom() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        getAllGroupsWithTasksUseCase
                            .getAllGroupsWithTasks().first()
                    )
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }
}