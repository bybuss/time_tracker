package com.example.time_tracker.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time_tracker.data.local.room.group.GroupRepository
import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import com.example.time_tracker.data.local.room.project.ProjectRepository
import com.example.time_tracker.data.local.room.role.RoleRepository
import com.example.time_tracker.data.local.room.task.TaskRepository
import com.example.time_tracker.data.local.room.user.UserRepository
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepository
import com.example.time_tracker.data.local.room.userTask.UserTaskRepository
import com.example.time_tracker.domain.network.GraphQLClient
import com.example.time_tracker.domain.useCase.graphQL.AddOrganizationUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddProjectUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddRoleUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddTaskUseCase
import com.example.time_tracker.domain.useCase.graphQL.AddUserUseCase
import com.example.time_tracker.domain.useCase.graphQL.AuthUserUseCase
import com.example.time_tracker.domain.useCase.graphQL.ChangePasswordUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetAllFullTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetAllSimpleTasksByAssignerIdUseCase
import com.example.time_tracker.domain.useCase.room.GetAllTasksFromRoomUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetFullTaskByIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.GetSimpleTaskByIdUseCase
import com.example.time_tracker.domain.useCase.graphQL.RefreshTokenUseCase
import com.example.time_tracker.domain.useCase.graphQL.RequestChangePasswordUseCase
import com.example.time_tracker.domain.useCase.room.GetAllGroupsWithTasksFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllOrganizationsFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllOrganizationsWithUsersFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllProjectsFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllRolesFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllTasksWithUsersFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUserOrgFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUserTaskFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersWithOrganizationsFromRoomUseCase
import com.example.time_tracker.domain.useCase.room.GetAllUsersWithTasksFromRoomUseCase
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

    private val getAllTasksFromRoomUseCase: GetAllTasksFromRoomUseCase,
    private val getAllRolesFromRoomUseCase: GetAllRolesFromRoomUseCase,
    private val getAllOrganizationsFromRoomUseCase: GetAllOrganizationsFromRoomUseCase,
    private val getAllProjectsFromRoomUseCase: GetAllProjectsFromRoomUseCase,
    private val getAllUsersFromRoomUseCase: GetAllUsersFromRoomUseCase,
    private val getAllTasksWithUsersFromRoomUseCase: GetAllTasksWithUsersFromRoomUseCase,
    private val getAllUsersWithTasksFromRoomUseCase: GetAllUsersWithTasksFromRoomUseCase,
    private val getAllUsersWithOrganizationsFromRoomUseCase: GetAllUsersWithOrganizationsFromRoomUseCase,
    private val getAllOrganizationsWithUsersFromRoomUseCase: GetAllOrganizationsWithUsersFromRoomUseCase,
    private val getAllUserOrgFromRoomUseCase: GetAllUserOrgFromRoomUseCase,
    private val getAllUserTaskFromRoomUseCase: GetAllUserTaskFromRoomUseCase,
    private val getAllGroupsWithTasksFromRoomUseCase: GetAllGroupsWithTasksFromRoomUseCase
): ViewModel() {

    private var _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Loading)
    var uiState = _uiState.asStateFlow()
    var token: String? = null
        private set

    companion object {
        const val TIMEOUT_MILLIS: Long = 5_000L
    }

    fun setToken(responseToken: String) {
        token = responseToken
    }

    suspend fun addRole(name: String, permissions: Map<String, Map<String, Boolean>>) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(addRoleUseCase.addRole(name, permissions))
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addOrganization(name: String, description: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(addOrganizationUseCase.addOrganization(name,  description))
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
                        addUserUseCase.addUser(firstName, lastName, roleId, email, password)
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
                    SignUpUiState.Success(authUserUseCase.authUser(email, password))
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
                    SignUpUiState.Success(refreshTokenUseCase.refreshToken())
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }

    suspend fun addProject(name: String, organizationId: Int, description: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        addProjectUseCase.addProject(name, organizationId, description)
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
                        getAllFullTasksByAssignerIdUseCase.getAllFullTasksByAssignerId(assignerId)
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
                    SignUpUiState.Success(getFullTaskByIdUseCase.getFullTaskById(id))
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
                        getAllSimpleTasksByAssignerIdUseCase.getAllSimpleTasksByAssignerId(assignerId)
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
                    SignUpUiState.Success(getSimpleTaskByIdUseCase.getSimpleTaskById(id))
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
                        requestChangePasswordUseCase.requestChangePassword(
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
                        SignUpUiState.Success(changePasswordUseCase.changePassword(newPassword, it))
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
                    SignUpUiState.Success(getAllTasksFromRoomUseCase.getAllTasks().first())
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
                     SignUpUiState.Success(getAllRolesFromRoomUseCase.getAllRoles().first())
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
                    SignUpUiState.Success(getAllOrganizationsFromRoomUseCase.getAllOrganizations().first())
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
                    SignUpUiState.Success(getAllProjectsFromRoomUseCase.getAllProjects().first())
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
                    SignUpUiState.Success(getAllUsersFromRoomUseCase.getAllUsers().first())
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
                        getAllTasksWithUsersFromRoomUseCase.getAllTasksWithUsers().first()
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
                        getAllUsersWithTasksFromRoomUseCase.getAllUsersWithTasks().first()
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
                        getAllUsersWithOrganizationsFromRoomUseCase.getAllUsersWithOrganizations().first()
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
                        getAllOrganizationsWithUsersFromRoomUseCase.getAllOrganizationsWithUsers().first()
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
                   SignUpUiState.Success(getAllUserOrgFromRoomUseCase.getAllUserOrg().first())
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
                   SignUpUiState.Success(getAllUserTaskFromRoomUseCase.getAllUserTask().first())
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
                        getAllGroupsWithTasksFromRoomUseCase.getAllGroupsWithTasks().first()
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