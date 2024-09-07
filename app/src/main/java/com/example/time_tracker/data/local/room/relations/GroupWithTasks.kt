package com.example.time_tracker.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.room.group.Group
import com.example.time_tracker.data.local.room.task.Task

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