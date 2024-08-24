package com.example.time_tracker.data

import android.content.Context
import android.provider.Settings
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.time_tracker.data.local.AppDatabase
import com.example.time_tracker.data.local.organization.OrganizationRepository
import com.example.time_tracker.data.local.organization.OrganizationRepositoryImpl
import com.example.time_tracker.data.local.project.ProjectRepository
import com.example.time_tracker.data.local.project.ProjectRepositoryImpl
import com.example.time_tracker.data.local.role.RoleRepository
import com.example.time_tracker.data.local.role.RoleRepositoryImpl
import com.example.time_tracker.data.local.task.TaskRepository
import com.example.time_tracker.data.local.task.TaskRepositoryImpl
import com.example.time_tracker.data.local.user.UserRepository
import com.example.time_tracker.data.local.user.UserRepositoryImpl
import com.example.time_tracker.data.network.GraphQLRepository
import com.example.time_tracker.data.network.TokenStoreRepository
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
    val tokenStoreRepository: TokenStoreRepository
    val graphQLRepository: GraphQLRepository

    val taskRepository: TaskRepository
    val roleRepository: RoleRepository
    val organizationRepository: OrganizationRepository
    val projectRepository: ProjectRepository
    val userRepository: UserRepository
}

class AppContainerImpl(private val context: Context): AppContainer {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val baseUrl = "http://31.128.45.95:8000/graphql"

    override val tokenStoreRepository: TokenStoreRepository by lazy {
        TokenStoreRepository(context)
    }

    private val accessToken = runBlocking {
        tokenStoreRepository.getAccessToken().first()
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

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ExtractRefreshTokenInterceptor(tokenStoreRepository, coroutineScope))
        .addInterceptor(AddRefreshTokenInterceptor(tokenStoreRepository))
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
            tokenStoreRepository,
            taskRepository,
            roleRepository,
            organizationRepository,
            projectRepository,
            userRepository
        )
    }

    private fun getDeviceFingerprint(context: Context): String {
        val fingerprint = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return fingerprint
    }
}