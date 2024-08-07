package com.example.time_tracker.data.network

import com.example.time_tracker.GetFullTaskByAssignerIdQuery
import com.example.time_tracker.GetSimpleTaskByAssignerIdQuery
import com.example.time_tracker.domain.model.FullTask
import com.example.time_tracker.domain.model.SimpleTask

/**
 * @author bybuss
*/

fun convertToJson(input: String): String {
    return input
        .replace("=", "\":\"")
        .replace(", ", "\", \"")
        .replace("{", "{\"")
        .replace("}", "\"}")
        .replace("}\"", "}")
        .replace("\":\"{", "\":{")
}

fun GetFullTaskByAssignerIdQuery.GetTask.toFulTask(): FullTask {
    return FullTask(
        id = id!!,
        name = name!!,
        description = description!!,
        isDone = isDone!!,
        addedAt = addedAt.toString(),
        doneAt = doneAt.toString(),
        assignerId = assignerId.toString(),
        color = color,
        duration = (duration ?: 0) as Int,
        difficulty = difficulty,
        projectId = projectId!!,
        groupId = groupId
    )
}

fun GetSimpleTaskByAssignerIdQuery.GetTask.toSimpleTask(): SimpleTask {
    return SimpleTask(
        id = id!!,
        name = name!!,
        isDone = isDone!!,
        addedAt = addedAt.toString(),
        doneAt = doneAt.toString() ?: ""
    )
}