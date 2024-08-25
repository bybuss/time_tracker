package com.example.time_tracker.data.network

import com.apollographql.apollo3.exception.ApolloException
import com.example.time_tracker.GetAllFullTasksByAssignerIdQuery
import com.example.time_tracker.GetFullTaskByIdQuery
import com.example.time_tracker.GetAllSimpleTasksByAssignerIdQuery
import com.example.time_tracker.GetSimpleTaskByIdQuery
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

fun GetAllFullTasksByAssignerIdQuery.GetTask.toFulTask(): FullTask {
    return FullTask(
        id = id!!,
        name = name!!,
        description = description!!,
        isDone = isDone!!,
        addedAt = addedAt.toString(),
        doneAt = doneAt.toString(),
        assignerId = assignerId.toString(),
        color = color,
        duration = (duration as? Int) ?: 0,
        difficulty = difficulty,
        projectId = projectId!!,
        groupId = groupId
    )
}

fun GetFullTaskByIdQuery.GetTask.toFullTask(): FullTask {
    return FullTask(
        id = id!!,
        name = name!!,
        description = description!!,
        isDone = isDone!!,
        addedAt = addedAt.toString(),
        doneAt = doneAt.toString(),
        assignerId = assignerId.toString(),
        color = color,
        duration = (duration as? Int) ?: 0,
        difficulty = difficulty,
        projectId = projectId!!,
        groupId = groupId
    )
}

fun GetAllSimpleTasksByAssignerIdQuery.GetTask.toSimpleTask(): SimpleTask {
    return SimpleTask(
        id = id!!,
        name = name!!,
        isDone = isDone ?: false,
        addedAt = addedAt.toString(),
        doneAt = doneAt.toString()
    )
}

fun GetSimpleTaskByIdQuery.GetTask.toSimpleTask(): SimpleTask {
    return SimpleTask(
        id = id!!,
        name = name!!,
        isDone = isDone ?: false,
        addedAt = addedAt.toString(),
        doneAt = doneAt.toString()
    )
}