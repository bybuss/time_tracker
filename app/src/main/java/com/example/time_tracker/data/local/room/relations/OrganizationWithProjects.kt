package com.example.time_tracker.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.room.organization.Organization
import com.example.time_tracker.data.local.room.project.Project

/**
 * @author bybuss
 */
data class OrganizationWithProjects(
    @Embedded val organization: Organization,
    @Relation(
        parentColumn = "id",
        entityColumn = "organizationId"
    )
    val projects: List<Project>
)