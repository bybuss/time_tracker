package com.example.time_tracker.data.local.user

import com.example.time_tracker.data.local.base.BaseRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
interface UserRepository: BaseRepository<User> {
    fun getAllUsers(): Flow<List<User>>
}