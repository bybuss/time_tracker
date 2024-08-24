package com.example.time_tracker.data.local.user

import com.example.time_tracker.data.local.base.BaseRepositoryImpl
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class UserRepositoryImpl(
    private val userDao: UserDao
): BaseRepositoryImpl<User>(userDao), UserRepository {
    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
}