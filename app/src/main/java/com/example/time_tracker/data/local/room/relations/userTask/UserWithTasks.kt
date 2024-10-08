package com.example.time_tracker.data.local.room.relations.userTask

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.time_tracker.data.local.room.task.Task
import com.example.time_tracker.data.local.room.user.User
import com.example.time_tracker.data.local.room.userTask.UserTask


/**
 * @author bybuss
 */
data class UserWithTasks (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserTask::class,
            parentColumn = "userId",
            entityColumn = "taskId"
        )
    )
    val tasks: List<Task>
)