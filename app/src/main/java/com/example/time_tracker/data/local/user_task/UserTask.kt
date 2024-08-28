package com.example.time_tracker.data.local.user_task

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.task.Task
import com.example.time_tracker.data.local.user.User

/**
 * @author bybuss
 */
@Entity(tableName = "users_tasks",
    foreignKeys = [
        ForeignKey(
            entity = Task::class, parentColumns = ["id"], childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class, parentColumns = ["id"], childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"], unique = true)]
)
data class UserTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val githubData: String,
    val taskId: Int, // ForeignKey
    val userId: String, // ForeignKey
    val isEmployee: Boolean = false
)