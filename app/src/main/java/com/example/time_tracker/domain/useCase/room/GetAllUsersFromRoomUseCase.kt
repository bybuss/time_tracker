package com.example.time_tracker.domain.useCase.room

import com.example.time_tracker.data.local.room.user.User
import com.example.time_tracker.data.local.room.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author bybuss
 */
class GetAllUsersFromRoomUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): Flow<List<User>> {
        return userRepository.getAllUsers()
    }
}