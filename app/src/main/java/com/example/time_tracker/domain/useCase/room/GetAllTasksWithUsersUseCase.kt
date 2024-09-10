package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.relations.userTask.TaskWithUsers
import com.example.time_tracker.data.local.room.task.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllTasksWithUsersUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    fun getAllTasksWithUsers(): Flow<List<TaskWithUsers>> {
        return taskRepository.getAllTasksWithUsers()
    }
}