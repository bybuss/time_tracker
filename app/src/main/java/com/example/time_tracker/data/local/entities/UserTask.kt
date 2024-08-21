package com.example.time_tracker.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "users_tasks", foreignKeys = [
    ForeignKey(
        entity = Task::class, parentColumns = ["id"], childColumns = ["taskId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = User::class, parentColumns = ["id"], childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )
])
data class UserTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val githubData: String,
    val taskId: Int, // ForeignKey()
    val userId: Int, // ForeignKey()
    val isEmployee: Boolean = false
)
