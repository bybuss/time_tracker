package com.example.time_tracker.data.local.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.base.BaseDao
import com.example.time_tracker.data.local.relations.userOrg.UserWithOrganizations
import com.example.time_tracker.data.local.relations.userTask.UserWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM users")
    fun getAllUsersWithOrganizations(): Flow<List<UserWithOrganizations>>

    @Transaction
    @Query("SELECT * FROM users")
    fun getAllUsersWithTasks(): Flow<List<UserWithTasks>>
}