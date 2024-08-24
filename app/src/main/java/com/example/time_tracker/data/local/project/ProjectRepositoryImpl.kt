package com.example.time_tracker.data.local.project

import com.example.time_tracker.data.local.base.BaseRepositoryImpl

/**
 * @author bybuss
 */
class ProjectRepositoryImpl(
    private val projectDao: ProjectDao
): BaseRepositoryImpl<Project>(projectDao), ProjectRepository {
}