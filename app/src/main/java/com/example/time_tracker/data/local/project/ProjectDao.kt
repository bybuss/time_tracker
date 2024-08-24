package com.example.time_tracker.data.local.project

import androidx.room.Dao
import androidx.room.Query
import com.example.time_tracker.data.local.base.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface ProjectDao: BaseDao<Project> {
    @Query("SELECT * FROM projects")
    fun getAllProjects(): Flow<List<Project>>
}