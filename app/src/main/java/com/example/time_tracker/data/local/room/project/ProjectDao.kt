package com.example.time_tracker.data.local.room.project

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.data.local.room.base.BaseDao
import com.example.time_tracker.data.local.room.relations.ProjectWithGroups
import com.example.time_tracker.data.local.room.relations.ProjectWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface ProjectDao: BaseDao<Project> {
    @Query("SELECT * FROM projects")
    fun getAllProjects(): Flow<List<Project>>

    @Transaction
    @Query("SELECT * FROM projects")
    fun getAllProjectsWithTasks(): Flow<List<ProjectWithTasks>>

    @Transaction
    @Query("SELECT * FROM projects")
    fun getAllProjectsWithGroups(): Flow<List<ProjectWithGroups>>
}