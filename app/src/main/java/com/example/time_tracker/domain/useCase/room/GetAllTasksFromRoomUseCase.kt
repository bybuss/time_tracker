package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.task.Task
import com.example.time_tracker.data.local.room.task.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllTasksFromRoomUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    fun getAllTasks(): Flow<List<Task>> {
        return taskRepository.getAllTasks()
    }
}