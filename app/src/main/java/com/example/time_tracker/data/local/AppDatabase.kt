package com.example.time_tracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.time_tracker.data.local.group.Group
import com.example.time_tracker.data.local.group.GroupDao
import com.example.time_tracker.data.local.organization.Organization
import com.example.time_tracker.data.local.organization.OrganizationDao
import com.example.time_tracker.data.local.project.Project
import com.example.time_tracker.data.local.project.ProjectDao
import com.example.time_tracker.data.local.role.Role
import com.example.time_tracker.data.local.role.RoleDao
import com.example.time_tracker.data.local.task.Task
import com.example.time_tracker.data.local.task.TaskDao
import com.example.time_tracker.data.local.user.User
import com.example.time_tracker.data.local.user.UserDao
import com.example.time_tracker.data.local.userOrg.UserOrg
import com.example.time_tracker.data.local.userOrg.UserOrgDao
import com.example.time_tracker.data.local.userTask.UserTask
import com.example.time_tracker.data.local.userTask.UserTaskDao

/**
 * @author bybuss
 */
@Database(
    entities = [
        Group::class,
        Organization::class,
        Project::class,
        Role::class,
        Task::class,
        User::class,
        UserOrg::class,
        UserTask::class
    ],
    version = 9,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun organizationDao(): OrganizationDao
    abstract fun projectDao(): ProjectDao
    abstract fun roleDao(): RoleDao
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
    abstract fun userOrgDao(): UserOrgDao
    abstract fun userTaskDao(): UserTaskDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "time_tracker")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}