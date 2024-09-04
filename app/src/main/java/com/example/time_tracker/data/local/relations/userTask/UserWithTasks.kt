package com.example.time_tracker.data.local.relations.userTask

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.time_tracker.data.local.task.Task
import com.example.time_tracker.data.local.user.User
import com.example.time_tracker.data.local.userTask.UserTask


/**
 * @author bybuss
 */
data class UserWithTasks (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(UserTask::class)
    )
    val tasks: List<Task>
)