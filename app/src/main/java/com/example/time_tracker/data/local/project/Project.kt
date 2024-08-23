package com.example.time_tracker.data.local.project

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.organization.Organization

/**
 * @author bybuss
 */
@Entity(tableName = "projects", foreignKeys = [
    ForeignKey(
        entity = Organization::class, parentColumns = ["id"], childColumns = ["organizationId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )
])
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val createdAt: String,
    val organizationId: Int
)