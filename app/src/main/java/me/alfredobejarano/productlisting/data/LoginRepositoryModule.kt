package me.alfredobejarano.productlisting.data

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Dagger module that defines how to inject the
 * necessary dependencies for the LoginRepository class.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 09:29 PM
 * @version 1.0
 **/
@Module
class LoginRepositoryModule(private val ctx: Context) {
    /**
     * Defines how a [SessionDao] is going to be injected.
     */
    @Provides
    @Singleton
    fun provideSessionDao(): SessionDao =
        AppDatabase.getInstance(ctx).getSessionDao()

    /**
     *Defines how a [LoginRepository] is going to be injected.
     */
    @Provides
    @Singleton
    fun provideLoginRepository(service: Webservice, dao: SessionDao) =
        LoginRepository(service, dao)
}