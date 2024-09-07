package com.example.time_tracker.data.local.room.project

import com.example.time_tracker.base.BaseRepositoryImpl
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class ProjectRepositoryImpl(
    private val projectDao: ProjectDao
): BaseRepositoryImpl<Project>(projectDao), ProjectRepository {
    override fun getAllProjects(): Flow<List<Project>> = projectDao.getAllProjects()
}