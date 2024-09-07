package com.example.time_tracker.data.local.room.user

import com.example.time_tracker.base.BaseRepository
import com.example.time_tracker.data.local.room.relations.userOrg.UserWithOrganizations
import com.example.time_tracker.data.local.room.relations.userTask.UserWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface UserRepository: BaseRepository<User> {
    fun getAllUsers(): Flow<List<User>>

    fun getAllUsersWithOrganizations(): Flow<List<UserWithOrganizations>>

    fun getAllUsersWithTasks(): Flow<List<UserWithTasks>>
}