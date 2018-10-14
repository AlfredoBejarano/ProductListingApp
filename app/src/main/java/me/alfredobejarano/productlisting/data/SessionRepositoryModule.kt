package me.alfredobejarano.productlisting.data

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Dagger module that defines how to inject the [SessionRepository] class.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 10:05 PM
 * @version 1.0
 **/
@Module
class SessionRepositoryModule(private val ctx: Context) {
    /**
     * Defines how a [SessionDao] is going to be injected.
     */
    @Provides
    @Singleton
    fun provideSessionDao(): SessionDao =
        AppDatabase.getInstance(ctx).getSessionDao()

    /**
     * Defines how a [SessionRepository] is going to be injected.
     */
    @Provides
    @Singleton
    fun provideSessionRepository(dao: SessionDao, ws: Webservice): SessionRepository =
        SessionRepository(dao, ws)
}