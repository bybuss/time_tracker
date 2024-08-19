package com.example.time_tracker.domain.model

/**
 * @author bybuss
 */
object UnauthorizedRole {
    val name = "unauthorized"
    val permissions = mapOf(
        "user" to mapOf("create" to true, "read" to false, "update" to false, "delete" to false),
        "task" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false),
        "group" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false),
        "project" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false),
        "organization" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false),
        "role" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false)
    )
}

object UserRole {
    val name = "user"
    val permissions = mapOf(
        "user" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "task" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "group" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "project" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "organization" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false),
        "role" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false)
    )
}

object EnterpriseRole {
    val name = "enterprise"
    val permissions = mapOf(
        "user" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "task" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "group" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "project" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "organization" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "role" to mapOf("create" to false, "read" to false, "update" to false, "delete" to false)
    )
}

object AdminRole {
    val name = "admin"
    val permissions = mapOf(
        "user" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "task" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "group" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "project" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "organization" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true),
        "role" to mapOf("create" to true, "read" to true, "update" to true, "delete" to true)
    )
}