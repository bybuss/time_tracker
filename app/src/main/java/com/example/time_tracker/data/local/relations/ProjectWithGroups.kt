package com.example.time_tracker.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.group.Group
import com.example.time_tracker.data.local.project.Project

/**
 * @author bybuss
 */
data class ProjectWithGroups(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "id",
        entityColumn = "projectId"
    )
    val groups: List<Group>
)