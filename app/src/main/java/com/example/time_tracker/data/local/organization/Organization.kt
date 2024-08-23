package com.example.time_tracker.data.local.organization

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "organizations")
data class Organization(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String
)