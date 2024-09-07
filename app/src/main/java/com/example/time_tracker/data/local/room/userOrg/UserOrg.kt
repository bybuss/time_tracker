package com.example.time_tracker.data.local.room.userOrg

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(
    tableName = "user_org",
    indices = [Index(value = ["userId", "orgId"], unique = true)]
)
data class UserOrg(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String, // ForeignKey ✅
    val orgId: Int, // ForeignKey ✅
    val position: String,
    val permissions: Map<String, Map<String, Boolean>>
)