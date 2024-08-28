package com.example.time_tracker.data.local.organization

import androidx.room.Dao
import androidx.room.Query
import com.example.time_tracker.base.BaseDao
import kotlinx.coroutines.flow.Flow

/**
 * @author bybuss
 */
@Dao
interface OrganizationDao: BaseDao<Organization> {
    @Query("SELECT * FROM organizations")
    fun getAllOrganizations(): Flow<List<Organization>>
}