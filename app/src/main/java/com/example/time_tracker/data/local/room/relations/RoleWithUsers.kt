package com.example.time_tracker.data.local.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.time_tracker.data.local.room.role.Role
import com.example.time_tracker.data.local.room.user.User

/**
 * @author bybuss
 */
data class RoleWithUsers(
    @Embedded val role: Role,
    @Relation(
        parentColumn = "id",
        entityColumn = "roleId"
    )
    val users: List<User>
)