package com.example.time_tracker.data.local.task

import com.example.time_tracker.data.local.base.BaseRepositoryImpl
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class TaskRepositoryImpl(
    private val taskDao: TaskDao
): BaseRepositoryImpl<Task>(taskDao), TaskRepository {
    override fun getFullTasksByAssignerId(assignerId: Int): Flow<List<FullTask>>
        = taskDao.getFullTasksByAssignerId(assignerId)

    override fun getFullTasksById(id: Int): Flow<List<FullTask>>
        = taskDao.getFullTasksById(id)

    override fun getSimpleTasksByAssignerId(assignerId: Int): Flow<List<SimpleTask>>
        = taskDao.getSimpleTasksByAssignerId(assignerId)

    override fun getSimpleTasksById(id: Int): Flow<List<SimpleTask>>
        = taskDao.getSimpleTasksById(id)

    override fun getAllTasks(): Flow<List<Task>>
        = taskDao.getAllTasks()
}