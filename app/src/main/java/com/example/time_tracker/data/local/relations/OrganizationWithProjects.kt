package com.example.time_tracker.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.organization.Organization
import com.example.time_tracker.data.local.project.Project

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