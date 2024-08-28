package com.example.time_tracker.data.local.task

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.group.Group
import com.example.time_tracker.data.local.project.Project
import com.example.time_tracker.data.local.user_task.UserTask

/**
 * @author bybuss
 */

/**
 * [assignerId], [projectId] и [groupId] могут быть null, потому что они могут не принадлежать ни к
 * какой группе или проекту, а в случае с создателем задачи, при его удалении задача может остаться
 * в проекте или группе.
 * */
@Entity(tableName = "tasks", foreignKeys = [
    ForeignKey(
        entity = UserTask::class, parentColumns = ["userId"], childColumns = ["assignerId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Project::class, parentColumns = ["id"], childColumns = ["projectId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Group::class, parentColumns = ["id"], childColumns = ["groupId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )
])
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val isDone: Boolean = false,
    val addedAt: String,
    val endDate: String? = null,
    val doneAt: String? = null,
    val assignerId: String? = null, // ForeignKey
    val duration: Int,
    val color: String,
    val difficulty: String,
    val projectId: Int? = null, // ForeignKey
    val groupId: Int? = null // ForeignKey
)