package com.example.time_tracker.data.local.group

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.project.Project
import com.example.time_tracker.data.local.user.User

/**
 * @author bybuss
 */
@Entity(tableName = "groups")
data class Group(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val userId: String, // ForeignKey
    val projectId: Int // ForeignKey ✅
)