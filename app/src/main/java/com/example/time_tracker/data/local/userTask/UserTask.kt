package com.example.time_tracker.data.local.userTask

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.task.Task
import com.example.time_tracker.data.local.user.User

/**
 * @author bybuss
 */
@Entity(
    tableName = "user_task",
    indices = [Index(value = ["taskId", "userId"], unique = true)]
)
data class UserTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val githubData: String,
    val taskId: Int, // ForeignKey ✅
    val userId: String, // ForeignKey ✅
    val isEmployee: Boolean = false
)