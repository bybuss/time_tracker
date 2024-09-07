package com.example.time_tracker.data.local.room.group

import com.example.time_tracker.base.BaseRepositoryImpl
import com.example.time_tracker.data.local.room.relations.GroupWithTasks
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
class GroupRepositoryImpl(
    private val groupDao: GroupDao
): BaseRepositoryImpl<Group>(groupDao), GroupRepository {
    override fun getAllGroupsWithTasks(): Flow<List<GroupWithTasks>>
        = groupDao.getAllGroupsWithTasks()
}