package com.example.time_tracker.data.local.room.project

import com.example.time_tracker.data.local.room.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface ProjectRepository: BaseRepository<Project> {
    fun getAllProjects(): Flow<List<Project>>
}