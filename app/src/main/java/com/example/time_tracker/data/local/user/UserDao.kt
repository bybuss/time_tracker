package com.example.time_tracker.data.local.user

import androidx.room.Dao
import androidx.room.Query
import com.example.time_tracker.data.local.base.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
}