package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.project.Project
import com.example.time_tracker.data.local.room.project.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllProjectsFromRoomUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {

    fun getAllProjects(): Flow<List<Project>> {
        return projectRepository.getAllProjects()
    }
}