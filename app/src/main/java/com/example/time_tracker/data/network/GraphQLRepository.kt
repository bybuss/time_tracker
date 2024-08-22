package com.example.time_tracker.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.AddOrganizationMutation
import com.example.time_tracker.AddProjectMutation
import com.example.time_tracker.AddRoleMutation
import com.example.time_tracker.AddTaskMutation
import com.example.time_tracker.AddUserMutation
import com.example.time_tracker.AuthUserQuery
import com.example.time_tracker.GetFullTasksByAssignerIdQuery
import com.example.time_tracker.GetSimpleTasksByAssignerIdQuery
import com.example.time_tracker.RefreshTokenQuery
import com.example.time_tracker.RequestChangePasswordQuery
import com.example.time_tracker.ChangePasswordMutation
import com.example.time_tracker.GetFullTasksByIdQuery
import com.example.time_tracker.GetSimpleTasksByIdQuery
import com.example.time_tracker.data.local.task.Task
import com.example.time_tracker.data.local.task.TaskRepository
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
    private val tokenStoreRepository: TokenStoreRepository,
    private val taskRepository: TaskRepository
): GraphQLClient {
    override suspend fun addRole(name: String, permissions: Map<String, Map<String, Boolean>>): Int {
        val response = apolloClient.mutation(AddRoleMutation(name, permissions)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.addRole?.id
            ?: throw ApolloException("Failed to add role: No ID returned")
    }

    override suspend fun addOrganization(name: String, description: String): Int {
        val response = apolloClient.mutation(AddOrganizationMutation(name, description)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.addOrganization?.id
            ?: throw ApolloException("Failed to add organization: No ID returned")
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

        return (response.data?.addUser?.id
            ?: throw ApolloException("Failed to add user: No ID returned")
        ).toString()
    }
// FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ, ОТЛАДКА
//  НЕ НУЖНА
    override suspend fun authUser(email: String, password: String): AccessToken {
        val response = apolloClient.query(AuthUserQuery(email, password))
            .addHttpHeader("Access-Token-Request", "true")
            .execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val responseJson = convertToJson((
                response.data?.authUser ?: throw ApolloException("User not found")
        ).toString())

        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken

        tokenStoreRepository.saveAccessToken(accessToken.token)
        tokenStoreRepository.saveAccessTokenExpiresTime(accessToken.expiresIn)
        tokenStoreRepository.saveAccessTokenExpiresTime(accessToken.createdAt)

        if (accessToken.token.isEmpty() || accessToken.token.isEmpty() || accessToken.token.isEmpty())
            throw ApolloException("Токен не был получен!")

// FIXME: УДАЛИТЬ return (НА ФИНАЛЬНОЙ СТАДИИ), ТК ОСНОВНОЙ ФУНКЦОНАЛ С СОХРАНЕНИЕМ ТОКЕНА ГОТОВ,
//  ОТЛАДКА НЕ НУЖНА
        return AccessToken(
            token = accessToken.token,
            expiresIn = accessToken.expiresIn,
            createdAt = accessToken.createdAt
        )
    }
// FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ, ОТЛАДКА
//  НЕ НУЖНА
    override suspend fun refreshToken(): AccessToken {
        val response = apolloClient.query(RefreshTokenQuery())
            .addHttpHeader("Refresh-Token-Request", "true")
            .execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        val responseJson = convertToJson((
                response.data?.refresh ?: throw ApolloException("User not found")
        ).toString())

        val accessToken = Json.decodeFromString<AuthUserResponse>(responseJson).accessToken

        tokenStoreRepository.saveAccessToken(accessToken.token)
        tokenStoreRepository.saveAccessTokenExpiresTime(accessToken.expiresIn)
        tokenStoreRepository.saveAccessTokenExpiresTime(accessToken.createdAt)

        if (accessToken.token.isEmpty() || accessToken.token.isEmpty() || accessToken.token.isEmpty()) {
            throw ApolloException("Токен не был получен!")
        }

// FIXME: УДАЛИТЬ return (НА ФИНАЛЬНОЙ СТАДИИ), ТК ОСНОВНОЙ ФУНКЦОНАЛ С СОХРАНЕНИЕМ ТОКЕНА ГОТОВ,
//  ОТЛАДКА НЕ НУЖНА
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

        return response.data?.addProject?.id
            ?: throw ApolloException("Failed to add project: No ID returned")
    }

    // FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК ИД НЕ БУДЕТ ВОЗВРАЩАТЬСЯ, А
    //  СОХРАНЯТЬСЯ ЛОКАЛЬНО
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
                assignees
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

        //TODO: НА МОМЕНТЕ СОХРАНЕНИЯ ЭТОГО ТАСКА И ЛОКАЛЬНО НЕОБХОДИМО БУДЕТ ПРИСВОИТЬ ЭТОТ ЖЕ ID
        // => НЕ НУЖНО БУДЕТ ВОЗВРАЩАТЬ, А СОХРАНЯТЬ ЛОКАЛЬНО
        return taskId
    }

    override suspend fun getFullTasksByAssignerId(assignerId: String): List<FullTask> {
        val response = apolloClient.query(GetFullTasksByAssignerIdQuery(assignerId)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.map { it.toFulTask() } ?: emptyList()
    }

    override suspend fun getFullTasksById(id: Int): List<FullTask> {
        val response = apolloClient.query(GetFullTasksByIdQuery(id)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.map { it.toFulTask() } ?: emptyList()
    }

    override suspend fun getSimpleTasksByAssignerId(assignerId: String): List<SimpleTask> {
        val response = apolloClient.query(GetSimpleTasksByAssignerIdQuery(assignerId)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?.map { it.toSimpleTask() } ?: emptyList()
    }

    override suspend fun getSimpleTasksById(id: Int): List<SimpleTask> {
        val response = apolloClient.query(GetSimpleTasksByIdQuery(id)).execute()

        if (response.hasErrors()) throw ApolloException(response.errors?.firstOrNull()?.message)

        return response.data?.getTask?. map { it.toSimpleTask() } ?: emptyList()
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