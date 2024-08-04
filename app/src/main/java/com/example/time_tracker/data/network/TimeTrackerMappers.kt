package com.example.time_tracker.data.network

import com.example.time_tracker.AuthUserQuery
import com.example.time_tracker.domain.model.AccessToken

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

//fun AddRoleMutation.AddRole.toRole(): Int {
//    return id!!.toInt()
//}