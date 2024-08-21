package com.example.time_tracker.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "user_org", foreignKeys = [
    ForeignKey(
        entity = User::class, parentColumns = ["id"], childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Organization::class, parentColumns = ["id"], childColumns = ["orgId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )
])
data class UserOrg(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val orgId: Int,
    val position: String,
    val permissions: Map<String, Map<String, Boolean>>
)
