package com.example.time_tracker.data.local.user_org

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.time_tracker.data.local.organization.Organization
import com.example.time_tracker.data.local.user.User

/**
 * @author bybuss
 */
@Entity(tableName = "user_org", //foreignKeys = [
//    ForeignKey(
//        entity = User::class, parentColumns = ["id"], childColumns = ["userId"],
//        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
//    ),
//    ForeignKey(
//        entity = Organization::class, parentColumns = ["id"], childColumns = ["orgId"],
//        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
//    )
//]
)
data class UserOrg(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val orgId: Int,
    val position: String,
    val permissions: Map<String, Map<String, Boolean>>
)