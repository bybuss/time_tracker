package com.example.time_tracker.data.local.userTask

import com.example.time_tracker.base.BaseRepositoryImpl

/**
 * @author bybuss
 */
class UserTaskRepositoryImpl(
    private val userTaskDao: UserTaskDao
): BaseRepositoryImpl<UserTask>(userTaskDao), UserTaskRepository {
}