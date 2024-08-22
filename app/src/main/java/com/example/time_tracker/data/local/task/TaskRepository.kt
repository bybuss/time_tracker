package com.example.time_tracker.data.local.task

import com.example.time_tracker.data.local.BaseRepository
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
}