package com.example.time_tracker.data.local.group

import com.example.time_tracker.base.BaseRepositoryImpl

/**
 * @author bybuss
 */
class GroupRepositoryImpl(
    private val groupDao: GroupDao
): BaseRepositoryImpl<Group>(groupDao), GroupRepository {
    // тут типа реализация крутого запроса, шо аж фуражка слетает!!!
}