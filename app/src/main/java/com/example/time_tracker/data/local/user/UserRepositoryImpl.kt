package com.example.time_tracker.data.local.user

import com.example.time_tracker.base.BaseRepositoryImpl
import com.example.time_tracker.data.local.relations.userOrg.UserWithOrganizations
import com.example.time_tracker.data.local.relations.userTask.UserWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class UserRepositoryImpl(
    private val userDao: UserDao
): BaseRepositoryImpl<User>(userDao), UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    override fun getAllUsersWithOrganizations(): Flow<List<UserWithOrganizations>>
        = userDao.getAllUsersWithOrganizations()

    override fun getAllUsersWithTasks(): Flow<List<UserWithTasks>> = userDao.getAllUsersWithTasks()
}