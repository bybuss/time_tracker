package com.example.time_tracker.data.local.project

import com.example.time_tracker.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface ProjectRepository: BaseRepository<Project> {
    fun getAllProjects(): Flow<List<Project>>
}