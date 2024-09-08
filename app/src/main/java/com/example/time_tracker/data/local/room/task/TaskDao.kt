package com.example.time_tracker.data.local.room.task

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.data.local.room.base.BaseDao
import com.example.time_tracker.data.local.room.relations.userTask.TaskWithUsers
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask
import kotlinx.coroutines.flow.Flow


/**
 * @author bybuss
 */
@Dao
interface TaskDao: BaseDao<Task> {
    @Query("SELECT * FROM tasks WHERE assignerId = :assignerId")
    fun getFullTasksByAssignerId(assignerId: Int): Flow<List<FullTask>>

    @Query("SELECT * FROM tasks WHERE assignerId = :id")
    fun getFullTasksById(id: Int): Flow<List<FullTask>>

    @Query("SELECT id, name, isDone, addedAt, doneAt FROM tasks WHERE assignerId = :assignerId")
    fun getSimpleTasksByAssignerId(assignerId: Int): Flow<List<SimpleTask>>

    @Query("SELECT id, name, isDone, addedAt, doneAt FROM tasks WHERE id = :id")
    fun getSimpleTasksById(id: Int): Flow<List<SimpleTask>>

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Transaction
    @Query("SELECT * FROM tasks")
    fun getAllTasksWithUsers(): Flow<List<TaskWithUsers>>
}