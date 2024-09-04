package com.example.time_tracker.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.project.Project
import com.example.time_tracker.data.local.task.Task

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
