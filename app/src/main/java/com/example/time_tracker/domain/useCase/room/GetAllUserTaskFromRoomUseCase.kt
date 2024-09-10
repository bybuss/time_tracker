package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.userTask.UserTask
import com.example.time_tracker.data.local.room.userTask.UserTaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllUserTaskFromRoomUseCase @Inject constructor(
    private val userTaskRepository: UserTaskRepository
) {

    fun getAllUserTask(): Flow<List<UserTask>> {
        return userTaskRepository.getAllUserTask()
    }
}