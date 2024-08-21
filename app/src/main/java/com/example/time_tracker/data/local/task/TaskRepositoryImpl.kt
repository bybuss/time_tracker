package com.example.time_tracker.data.local.task

import com.example.time_tracker.data.local.BaseRepositoryImpl

/**
 * @author bybuss
 */
class TaskRepositoryImpl(
    private val taskDao: TaskDao
): BaseRepositoryImpl<Task>(taskDao), TaskRepository {
}