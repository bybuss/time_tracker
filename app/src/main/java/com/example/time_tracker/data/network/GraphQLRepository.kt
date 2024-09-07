package com.example.time_tracker.data.network

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.AddOrganizationMutation
import com.example.time_tracker.AddProjectMutation
import com.example.time_tracker.AddRoleMutation
import com.example.time_tracker.AddTaskMutation
import com.example.time_tracker.AddUserMutation
import com.example.time_tracker.AuthUserQuery
import com.example.time_tracker.GetAllFullTasksByAssignerIdQuery
import com.example.time_tracker.GetAllSimpleTasksByAssignerIdQuery
import com.example.time_tracker.RefreshTokenQuery
import com.example.time_tracker.RequestChangePasswordQuery
import com.example.time_tracker.ChangePasswordMutation
import com.example.time_tracker.GetFullTaskByIdQuery
import com.example.time_tracker.GetSimpleTaskByIdQuery
import com.example.time_tracker.data.local.dataStore.TokenDataSourceImpl
import com.example.time_tracker.data.local.room.group.GroupRepository
import com.example.time_tracker.data.local.room.organization.Organization
import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import com.example.time_tracker.data.local.room.project.Project
import com.example.time_tracker.data.local.room.project.ProjectRepository
import com.example.time_tracker.data.local.room.role.Role
import com.example.time_tracker.data.local.room.role.RoleRepository
import com.example.time_tracker.data.local.room.task.Task
import com.example.time_tracker.data.local.room.task.TaskRepository
import com.example.time_tracker.data.local.room.user.User
import com.example.time_tracker.data.local.room.user.UserRepository
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepository
import com.example.time_tracker.data.local.room.userTask.UserTaskRepository
import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.model.AuthUserResponse
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask
import com.example.time_tracker.domain.network.GraphQLClient
import kotlinx.serialization.json.Json

/**
 * @author bybuss
 */
class GraphQLRepository(
    private val apolloClient: ApolloClient,
    private val tokenDataSource: TokenDataSourceImpl,
    private val taskRepository: TaskRepository,
    private val roleRepository: RoleRepository,
    private val organizationRepository: OrganizationRepository,
    private val projectRepository: ProjectRepository,
    private val userRepository: UserRepository,
    private val userOrgRepository: UserOrgRepository,
    private val userTaskRepository: UserTaskRepository,
    private val groupRepository: GroupRepository,
): GraphQLClient {
    override suspend fun addRole(name: String, permissions: Map<String, Map<String, Boolean>>): Int {
        val response = apolloClient.mutation(AddRoleMutation(name, permissions)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val roleId = response.data?.addRole?.id
            ?: throw ApolloException("Failed to add role: No ID returned")

        roleRepository.insert(
            Role(
                id = roleId,
                name = name,
                permissions = permissions
            )
        )

        return roleId
    }

    override suspend fun addOrganization(name: String, description: String): Int {
        val response = apolloClient.mutation(AddOrganizationMutation(name, description)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val organizationId = response.data?.addOrganization?.id
            ?: throw ApolloException("Failed to add organization: No ID returned")

        organizationRepository.insert(
            Organization(
                id = organizationId,
                name = name,
                description = description
            )
        )

        return organizationId
    }

    override suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ): String {
        val response = apolloClient.mutation(AddUserMutation(
            firstName,
            lastName,
            roleId,
            email,
            password
        )).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val userId = (response.data?.addUser?.id
            ?: throw ApolloException("Failed to add user: No ID returned")
        ).toString()

        userRepository.insert(
            User (
                id = userId,
                firstName = firstName,
                lastName = lastName,
                roleId = roleId,
                email = email,
                registeredAt = "сейчас епта!",
                hashedPassword = password,
            )
        )

        return userId
    }

    override suspend fun authUser(email: String, password: String): AccessToken {
        val response = apolloClient.query(AuthUserQuery(email, password))
            .addHttpHeader("Access-Token-Request", "true")
            .execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val responseJson = convertToJson((
                response.data?.authUser ?: throw ApolloException("User not found")
        ).toString())

        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken

        tokenDataSource.saveAccessToken(accessToken.token)
        Log.d("TokenStoreRepository", "saveAccessToken from authUser: ${accessToken.token}")
        tokenDataSource.saveAccessTokenExpiresTime(accessToken.expiresIn)
        Log.d("TokenStoreRepository", "saveAccessTokenExpiresTime from authUser: ${accessToken.expiresIn}")
        tokenDataSource.saveAccessTokenCreatedTime(accessToken.createdAt)
        Log.d("TokenStoreRepository", "saveAccessTokenExpiresTime from authUser: ${accessToken.createdAt}")

        if (accessToken.token.isEmpty() || accessToken.token.isEmpty() || accessToken.token.isEmpty())
            throw ApolloException("Токен не был получен!")

        return AccessToken(
            token = accessToken.token,
            expiresIn = accessToken.expiresIn,
            createdAt = accessToken.createdAt
        )
    }

    override suspend fun refreshToken(): AccessToken {
        val response = apolloClient.query(RefreshTokenQuery())
            .addHttpHeader("Refresh-Token-Request", "true")
            .execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val responseJson = convertToJson((
                response.data?.refresh ?: throw ApolloException("User not found")
        ).toString())

        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken

    tokenDataSource.saveAccessToken(accessToken.token)
    Log.d("TokenStoreRepository", "saveAccessToken from refreshToken: ${accessToken.token}")
    tokenDataSource.saveAccessTokenExpiresTime(accessToken.expiresIn)
    Log.d("TokenStoreRepository", "saveAccessTokenExpiresTime from refreshToken: ${accessToken.expiresIn}")
    tokenDataSource.saveAccessTokenCreatedTime(accessToken.createdAt)
    Log.d("TokenStoreRepository", "saveAccessTokenExpiresTime from refreshToken: ${accessToken.createdAt}")

        if (accessToken.token.isEmpty() || accessToken.token.isEmpty() || accessToken.token.isEmpty()) {
            throw ApolloException("Токен не был получен!")
        }

        return AccessToken(
            token = accessToken.token,
            expiresIn = accessToken.expiresIn,
            createdAt = accessToken.createdAt
        )
    }

    override suspend fun addProject(name: String, organizationId: Int, description: String): Int {
        val response = apolloClient.mutation(
            AddProjectMutation(name, organizationId, description)
        ).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val projectId = response.data?.addProject?.id
            ?: throw ApolloException("Failed to add project: No ID returned")

        projectRepository.insert(
            Project(
                id = projectId,
                name = name,
                organizationId = organizationId,
                createdAt = "сейчас епта!",
                description = description
            )
        )

        return projectId
    }

    override suspend fun addTask(
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
    ): Int {
        val response  = apolloClient.mutation(
            AddTaskMutation(
                name,
                description,
                isDone,
                assignerId,
                color,
                duration,
                Optional.presentIfNotNull(endDate),
                difficulty,
                projectId,
                Optional.presentIfNotNull(groupId),
                Optional.presentIfNotNull(assignees)
            )
        ).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val taskId = response.data?.addTask?.id
            ?: throw ApolloException("Failed to add task: No ID returned")

        taskRepository.insert(
            Task(
                id = taskId,
                name = name,
                description = description,
                isDone = isDone,
                addedAt = "сейчас, епта!",
                endDate = endDate,
                assignerId = assignerId,
                duration = duration,
                color = color,
                difficulty = difficulty,
                projectId = projectId,
                groupId = groupId,
//                assignees = assignees
            )
        )

        return taskId
    }

    override suspend fun getAllFullTasksByAssignerId(assignerId: String): List<FullTask> {
        val response = apolloClient.query(GetAllFullTasksByAssignerIdQuery(assignerId)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.map { it.toFulTask() } ?: emptyList()
    }

    override suspend fun getFullTaskById(id: Int): FullTask {
        val response = apolloClient.query(GetFullTaskByIdQuery(id)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.firstOrNull()?.toFullTask()
            ?: throw ApolloException("Нет такой задачи!")
    }

    override suspend fun getAllSimpleTasksByAssignerId(assignerId: String): List<SimpleTask> {
        val response = apolloClient.query(GetAllSimpleTasksByAssignerIdQuery(assignerId)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.map { it.toSimpleTask() } ?: emptyList()
    }

    override suspend fun getSimpleTaskById(id: Int): SimpleTask {
        val response = apolloClient.query(GetSimpleTaskByIdQuery(id)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.firstOrNull()?.toSimpleTask()
            ?: throw ApolloException("Нет такой задачи!")
    }

    override suspend fun requestChangePassword(
        id: String,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean {
        val response = apolloClient.query(RequestChangePasswordQuery(
            id,
            firstName,
            lastName,
            email
        )).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.requestChangePassword ?: false
    }

    override suspend fun changePassword(newPassword: String, changePasswordToken: String): Boolean {
        val response = apolloClient.mutation(ChangePasswordMutation(newPassword, changePasswordToken)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.changePassword ?: false
    }
}