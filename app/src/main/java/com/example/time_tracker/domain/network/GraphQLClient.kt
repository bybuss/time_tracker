package com.example.time_tracker.domain.network

import com.example.time_tracker.domain.model.AccessToken
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask

/**
 * @author bybuss
 */
interface GraphQLClient {

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
    suspend fun addRole(name: String, permissions: Map<String, Map<String, Boolean>>): Int

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
    suspend fun addOrganization(name: String, description: String): Int

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
    suspend fun addUser(
        firstName: String,
        lastName: String,
        roleId: Int,
        email: String,
        password: String
    ): String

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
    suspend fun authUser(email: String, password: String): AccessToken

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
    suspend fun refreshToken(): AccessToken

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
    suspend fun addProject(name: String, organizationId: Int, description: String): Int

    //FIXME: УБРАТЬ ТИП ВОЗРАЩАЕМЫХ ДАННЫХ (НА ФИНАЛЬНОЙ СТАДИИ), ТК НЕЧЕГО БУДЕТ ВОЗВРАЩАТЬ,
    // ОТЛАДКА НЕ НУЖНА
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
    ): Int

    suspend fun getFullTasksByAssignerId(assignerId: String): List<FullTask>

    suspend fun getFullTasksById(id: Int): List<FullTask>

    suspend fun getSimpleTasksByAssignerId(assignerId: String): List<SimpleTask>

    suspend fun getSimpleTasksById(id: Int): List<SimpleTask>

    suspend fun requestChangePassword(
        id: String,
        firstName: String,
        lastName: String,
        email: String
    ): Boolean

    suspend fun changePassword(newPassword: String, changePasswordToken: String): Boolean
}