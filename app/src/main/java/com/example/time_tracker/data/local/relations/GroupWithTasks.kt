package com.example.time_tracker.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.group.Group
import com.example.time_tracker.data.local.task.Task

/**
 * @author bybuss
 */
data class GroupWithTasks(
    @Embedded val group: Group,
    @Relation(
        parentColumn = "id",
        entityColumn = "groupId"
    )
    val tasks: List<Task>
)