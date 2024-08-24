package com.example.time_tracker.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author bybuss
 */
class Converters {
    @TypeConverter
    fun fromPermissionsMap(value: Map<String, Map<String, Boolean>>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPermissionsMap(value: String): Map<String, Map<String, Boolean>> {
        val gson = Gson()
        val mapType = object: TypeToken<Map<String, Map<String, Boolean>>>() {}.type
        return gson.fromJson(value, mapType)
    }
}