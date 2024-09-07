package com.example.time_tracker.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.room.project.Project
import com.example.time_tracker.data.local.room.task.Task

/**
 * @author bybuss
 */
data class ProjectWithTasks(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "projectId"
    )
    val tasks: List<Task>
)