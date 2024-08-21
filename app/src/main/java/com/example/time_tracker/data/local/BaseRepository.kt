package com.example.time_tracker.data.local

/**
 * @author bybuss
 */
interface BaseRepository<T> {
    suspend fun insert(entity: T)

    suspend fun insertAll(entities: List<T>)

    suspend fun update(entity: T)

    suspend fun delete(entity: T)
}