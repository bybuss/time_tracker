package com.example.time_tracker.di

import android.content.Context
import android.provider.Settings
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.time_tracker.data.local.dataStore.TokenDataSourceImpl
import com.example.time_tracker.data.local.room.AppDatabase
import com.example.time_tracker.data.local.room.group.GroupRepository
import com.example.time_tracker.data.local.room.group.GroupRepositoryImpl
import com.example.time_tracker.data.local.room.organization.OrganizationRepository
import com.example.time_tracker.data.local.room.organization.OrganizationRepositoryImpl
import com.example.time_tracker.data.local.room.project.ProjectRepository
import com.example.time_tracker.data.local.room.project.ProjectRepositoryImpl
import com.example.time_tracker.data.local.room.role.RoleRepository
import com.example.time_tracker.data.local.room.role.RoleRepositoryImpl
import com.example.time_tracker.data.local.room.task.TaskRepository
import com.example.time_tracker.data.local.room.task.TaskRepositoryImpl
import com.example.time_tracker.data.local.room.user.UserRepository
import com.example.time_tracker.data.local.room.user.UserRepositoryImpl
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepository
import com.example.time_tracker.data.local.room.userOrg.UserOrgRepositoryImpl
import com.example.time_tracker.data.local.room.userTask.UserTaskRepository
import com.example.time_tracker.data.local.room.userTask.UserTaskRepositoryImpl
import com.example.time_tracker.data.network.GraphQLRepository
import com.example.time_tracker.data.network.TokenDataSourceRepository
import com.example.time_tracker.data.network.interceptors.ExtractRefreshTokenInterceptor
import com.example.time_tracker.data.network.interceptors.AddRefreshTokenInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * @author bybuss
*/

interface AppContainer {
    val tokenDataSource: TokenDataSourceImpl
    val graphQLRepository: GraphQLRepository

    val taskRepository: TaskRepository
    val roleRepository: RoleRepository
    val organizationRepository: OrganizationRepository
    val projectRepository: ProjectRepository
    val userRepository: UserRepository
    val userOrgRepository: UserOrgRepository
    val userTaskRepository: UserTaskRepository
    val groupRepository: GroupRepository
}

class AppContainerImpl(private val context: Context): AppContainer {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val baseUrl = "http://31.128.45.95:8000/graphql"

    override val tokenDataSource: TokenDataSourceImpl by lazy {
        TokenDataSourceImpl(context)
    }
    private val accessToken = runBlocking {
        tokenDataSource.getAccessToken().first()
    }

    override val taskRepository: TaskRepository by lazy {
        TaskRepositoryImpl(AppDatabase.getDatabase(context).taskDao())
    }
    override val roleRepository: RoleRepository by lazy {
        RoleRepositoryImpl(AppDatabase.getDatabase(context).roleDao())
    }
    override val organizationRepository: OrganizationRepository by lazy {
        OrganizationRepositoryImpl(AppDatabase.getDatabase(context).organizationDao())
    }
    override val projectRepository: ProjectRepository by lazy {
        ProjectRepositoryImpl(AppDatabase.getDatabase(context).projectDao())
    }
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(AppDatabase.getDatabase(context).userDao())
    }
    override val userOrgRepository: UserOrgRepository by lazy {
        UserOrgRepositoryImpl(AppDatabase.getDatabase(context).userOrgDao())
    }
    override val userTaskRepository: UserTaskRepository by lazy {
        UserTaskRepositoryImpl(AppDatabase.getDatabase(context).userTaskDao())
    }
    override val groupRepository: GroupRepository by lazy {
        GroupRepositoryImpl(AppDatabase.getDatabase(context).groupDao())
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ExtractRefreshTokenInterceptor(tokenDataSource, coroutineScope))
        .addInterceptor(AddRefreshTokenInterceptor(tokenDataSource))
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", accessToken)
                .addHeader("fingerprint", getDeviceFingerprint(context))
                .build()
            chain.proceed(request)
        })
        .build()

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(baseUrl)
        .okHttpClient(okHttpClient)
        .build()

    override val graphQLRepository: GraphQLRepository by lazy {
        GraphQLRepository(
            apolloClient,
            tokenDataSource,
            taskRepository,
            roleRepository,
            organizationRepository,
            projectRepository,
            userRepository,
            userOrgRepository,
            userTaskRepository,
            groupRepository
        )
    }

    private fun getDeviceFingerprint(context: Context): String {
        val fingerprint = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return fingerprint
    }
}