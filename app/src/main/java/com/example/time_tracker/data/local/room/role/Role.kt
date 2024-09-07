package com.example.time_tracker.data.local.room.role

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "roles")
data class Role(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val permissions: Map<String, Map<String, Boolean>>
)