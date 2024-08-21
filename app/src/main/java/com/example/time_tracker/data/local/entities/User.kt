package com.example.time_tracker.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @author bybuss
 */
@Entity(tableName = "users", foreignKeys = [
    ForeignKey(
        entity = Role::class, parentColumns = ["id"], childColumns = ["roleId"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )
])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val roleId: Int = 1,
    val email: String,
    val isEmailConfirmed: Boolean = false,
    val registeredAt: String,
    val hashedPassword: String,
    val isActive: Boolean = true,
    val isVerified: Boolean = false,
    val tgId: String? = null,
    val tgSettings: String? = null,
    val pathFile: String? = null,
    val githubName: String? = null,
)