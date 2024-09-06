package com.example.time_tracker.data.local.relations.userOrg

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.time_tracker.data.local.organization.Organization
import com.example.time_tracker.data.local.user.User
import com.example.time_tracker.data.local.userOrg.UserOrg

/**
 * @author bybuss
 */
data class UserWithOrganizations (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserOrg::class,
            parentColumn = "userId",
            entityColumn = "orgId"
        )
    )
    val organizations: List<Organization>
)