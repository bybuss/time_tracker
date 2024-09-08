package com.example.time_tracker.data.local.room.task

import com.example.time_tracker.data.local.room.base.BaseRepository
import com.example.time_tracker.data.local.room.relations.userTask.TaskWithUsers
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface TaskRepository: BaseRepository<Task> {
    fun getFullTasksByAssignerId(assignerId: Int): Flow<List<FullTask>>

    fun getFullTasksById(id: Int): Flow<List<FullTask>>

    fun getSimpleTasksByAssignerId(assignerId: Int): Flow<List<SimpleTask>>

    fun getSimpleTasksById(id: Int): Flow<List<SimpleTask>>

    fun getAllTasks(): Flow<List<Task>>

    fun getAllTasksWithUsers(): Flow<List<TaskWithUsers>>
}