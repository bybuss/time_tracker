package com.example.time_tracker.data.local.user

import com.example.time_tracker.data.local.BaseRepositoryImpl

/**
 * @author bybuss
 */
class UserRepositoryImpl(
    private val userDao: UserDao
): BaseRepositoryImpl<User>(userDao), UserRepository {
}