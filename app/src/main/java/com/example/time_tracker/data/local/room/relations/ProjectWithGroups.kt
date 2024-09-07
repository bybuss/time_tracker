package com.example.time_tracker.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.room.group.Group
import com.example.time_tracker.data.local.room.project.Project

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