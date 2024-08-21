package com.example.time_tracker.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "groups", foreignKeys = [
    ForeignKey(
        entity = Project::class, parentColumns = ["id"], childColumns = ["projectId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE

    )
])
data class Group(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val userId: Int,
    val projectId: Int
)
