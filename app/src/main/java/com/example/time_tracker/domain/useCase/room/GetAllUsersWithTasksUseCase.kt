package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.relations.userTask.UserWithTasks
import com.example.time_tracker.data.local.room.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllUsersWithTasksUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun getAllUsersWithTasks(): Flow<List<UserWithTasks>> {
        return userRepository.getAllUsersWithTasks()
    }
}