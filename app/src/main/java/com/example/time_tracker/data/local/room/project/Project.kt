package com.example.time_tracker.data.local.room.project

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.room.organization.Organization

/**
 * @author bybuss
 */
@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val createdAt: String,
    val organizationId: Int // ForeignKey ✅
)