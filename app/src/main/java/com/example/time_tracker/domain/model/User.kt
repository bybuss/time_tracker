package com.example.time_tracker.domain.model

/**
 * @author bybuss
 */
data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val roleId: Int = 1,
    val email: String = "",
    val isActive: Boolean = false,
    val isSuperuser: Boolean = false,
    val isVerified: Boolean = false,
    val pathfile: String = ""
)
