package com.example.time_tracker.data.local.task

import androidx.room.Query
import com.example.time_tracker.data.local.BaseDao
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask
import kotlinx.coroutines.flow.Flow


/**
 * @author bybuss
 */
interface TaskDao: BaseDao<Task> {
    @Query("SELECT * FROM tasks WHERE assignerId = :assignerId")
    fun getFullTasksByAssignerId(assignerId: Int): Flow<List<FullTask>>

    @Query("SELECT * FROM tasks WHERE assignerId = :id")
    fun getFullTasksById(id: Int): Flow<List<FullTask>>

    @Query("SELECT id, name, isDone, addedAt, doneAt FROM tasks WHERE assignerId = :assignerId")
    fun getSimpleTasksByAssignerId(assignerId: Int): Flow<List<SimpleTask>>

    @Query("SELECT id, name, isDone, addedAt, doneAt FROM tasks WHERE id = :id")
    fun getSimpleTasksById(id: Int): Flow<List<SimpleTask>>
}