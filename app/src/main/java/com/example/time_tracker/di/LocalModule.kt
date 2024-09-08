package com.example.time_tracker.di

import android.content.Context
import androidx.room.Room
import com.example.time_tracker.data.local.room.AppDatabase
import com.example.time_tracker.data.local.room.group.GroupDao
import com.example.time_tracker.data.local.room.organization.OrganizationDao
import com.example.time_tracker.data.local.room.project.ProjectDao
import com.example.time_tracker.data.local.room.role.RoleDao
import com.example.time_tracker.data.local.room.task.TaskDao
import com.example.time_tracker.data.local.room.user.UserDao
import com.example.time_tracker.data.local.room.userOrg.UserOrgDao
import com.example.time_tracker.data.local.room.userTask.UserTaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author bybuss
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase (@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "time_tracker")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGroupDao(appDatabase: AppDatabase): GroupDao = appDatabase.groupDao()

    @Provides
    @Singleton
    fun provideOrganizationDao(appDatabase: AppDatabase): OrganizationDao = appDatabase.organizationDao()

    @Provides
    @Singleton
    fun provideProjectDao(appDatabase: AppDatabase): ProjectDao = appDatabase.projectDao()

    @Provides
    @Singleton
    fun provideRoleDao(appDatabase: AppDatabase): RoleDao = appDatabase.roleDao()

    @Provides
    @Singleton
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    @Singleton
    fun provideUserOrgDao(appDatabase: AppDatabase): UserOrgDao = appDatabase.userOrgDao()

    @Provides
    @Singleton
    fun provideUserTaskDao(appDatabase: AppDatabase): UserTaskDao = appDatabase.userTaskDao()
}