package com.example.time_tracker.data.local.project

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.time_tracker.base.BaseDao
import com.example.time_tracker.data.local.relations.ProjectWithGroups
import com.example.time_tracker.data.local.relations.ProjectWithTasks
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