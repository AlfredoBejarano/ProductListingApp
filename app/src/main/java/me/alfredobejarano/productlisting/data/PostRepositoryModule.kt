package me.alfredobejarano.productlisting.data

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Module for the [PostRepository] class.
 * This class defines the dependency graph for said
 * class using Dagger.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 05:53 PM
 * @version 1.0
 **/
@Module
class PostRepositoryModule(private val ctx: Context) {
    /**
     * Defines how a [PostDao] is going to be injected.
     */
    @Provides
    @Singleton
    fun providePostDao(): PostDao =
        AppDatabase.getInstance(ctx).getPostDao()

    /**
     * Defines how a [SessionRepository] is going to be injected.
     */
    @Provides
    @Singleton
    fun providePostRepository(dao: PostDao, ws: Webservice): PostRepository =
        PostRepository(ws, dao)
}