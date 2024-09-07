package com.example.time_tracker.data.local.room.relations.userOrg

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.time_tracker.data.local.room.organization.Organization
import com.example.time_tracker.data.local.room.user.User
import com.example.time_tracker.data.local.room.userOrg.UserOrg

/**
 * @author bybuss
 */
data class OrganizationWithUsers (
    @Embedded val organization: Organization,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserOrg::class,
            parentColumn = "orgId",
            entityColumn = "userId"
        )
    )
    val users: List<User>
)