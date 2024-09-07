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
import com.example.time_tracker.data.network.GraphQLRepository
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

/**
 * @author bybuss
*/

sealed interface SignUpUiState {
    data class Success(val data: Any?): SignUpUiState
    data class Error(val exception: String): SignUpUiState
    object Loading: SignUpUiState
}

class SignUpViewModel(
    private val graphQLRepository: GraphQLRepository,
    private val taskRepository: TaskRepository,
    private val roleRepository: RoleRepository,
    private val organizationRepository: OrganizationRepository,
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository,
    private val userOrgRepository: UserOrgRepository,
    private val userTaskRepository: UserTaskRepository,
    private val groupRepository: GroupRepository
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
                    SignUpUiState.Success(
                        graphQLRepository.addRole(
                            name,
                            permissions
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
                    SignUpUiState.Success(
                        graphQLRepository.authUser(
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

    suspend fun refreshToken() {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    //delay(500)
                    SignUpUiState.Success(graphQLRepository.refreshToken())
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
                        graphQLRepository.addUser(
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

    suspend fun addOrganization(name: String, description: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        graphQLRepository.addOrganization(
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

    suspend fun addProject(name: String, organizationId: Int, description: String) {
        viewModelScope.launch {
            _uiState.value = SignUpUiState.Loading
            _uiState.value = try {
                withTimeout(TIMEOUT_MILLIS) {
                    SignUpUiState.Success(
                        graphQLRepository.addProject(
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
                        graphQLRepository.addTask(
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
                    SignUpUiState.Success(graphQLRepository.getAllFullTasksByAssignerId(assignerId))
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
                    SignUpUiState.Success(graphQLRepository.getFullTaskById(id))
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
                    SignUpUiState.Success(graphQLRepository.getAllSimpleTasksByAssignerId(assignerId))
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
                    SignUpUiState.Success(graphQLRepository.getSimpleTaskById(id))
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
                        graphQLRepository.requestChangePassword(
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
                        SignUpUiState.Success(graphQLRepository.changePassword(newPassword, it))
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
                    SignUpUiState.Success(taskRepository.getAllTasks().first())
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
                     SignUpUiState.Success(roleRepository.getAllRoles().first())
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
                    SignUpUiState.Success(organizationRepository.getAllOrganizations().first())
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
                    SignUpUiState.Success(projectRepository.getAllProjects().first())
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
                    SignUpUiState.Success(userRepository.getAllUsers().first())
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
                    SignUpUiState.Success(taskRepository.getAllTasksWithUsers().first())
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
                    SignUpUiState.Success(userRepository.getAllUsersWithTasks().first())
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
                    SignUpUiState.Success(userRepository.getAllUsersWithOrganizations().first())
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
                    SignUpUiState.Success(organizationRepository.getAllOrganizationsWithUsers().first())
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
                   SignUpUiState.Success(userOrgRepository.getAllUserOrg().first())
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
                   SignUpUiState.Success(userTaskRepository.getAllUserTask().first())
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
                    SignUpUiState.Success(groupRepository.getAllGroupsWithTasks().first())
                }
            } catch (e: TimeoutCancellationException) {
                SignUpUiState.Error("Время ожидания ответа истекло: ${e.message.toString()}")
            } catch (e: Exception) {
                SignUpUiState.Error(e.message.toString())
            }
        }
    }
}