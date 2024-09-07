package com.example.time_tracker.di

import android.content.Context
import com.example.time_tracker.data.local.dataStore.TokenDataSource
import com.example.time_tracker.data.local.dataStore.TokenDataSourceImpl
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
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTokenDataSource(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    //FIXME: ИСПРАВИТЬ ВСЕ РЕПОЗИТОРИИ НИЖЕ, ОНИ ИМПЛЕМЕНТИРУЮТСЯ С ПОМОЩЬЮ ИНСТАНСА БД
    @Provides
    @Singleton
    fun provideTaskRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideRoleRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideOrganizationRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideProjectRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideUserRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideUserOrgRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideUserTaskRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)
    @Provides
    @Singleton
    fun provideGroupRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideGraphQLRepository(@ApplicationContext context: Context): TokenDataSource = TokenDataSourceImpl(context)
}