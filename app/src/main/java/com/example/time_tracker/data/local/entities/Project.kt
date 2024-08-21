package com.example.time_tracker.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
