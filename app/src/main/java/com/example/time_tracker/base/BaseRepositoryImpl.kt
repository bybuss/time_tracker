package com.example.time_tracker.base

/**
 * @author bybuss
 */
open class BaseRepositoryImpl<T>(private val dao: BaseDao<T>): BaseRepository<T> {
    override suspend fun insert(entity: T) = dao.insert(entity)

//    override suspend fun insertAll(entities: List<T>) = dao.insertAll(entities)

    override suspend fun update(entity: T) = dao.update(entity)

    override suspend fun delete(entity: T) = dao.delete(entity)
}