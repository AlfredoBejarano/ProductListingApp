package me.alfredobejarano.productlisting.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 * Dagger module that defines how to inject the [LoginRepository] class.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 09:29 PM
 * @version 1.0
 **/
@Module
class LoginRepositoryModule {
    /**
     *Defines how a [LoginRepository] is going to be injected.
     */
    @Provides
    @Singleton
    fun provideLoginRepository(service: Webservice, repo: SessionRepository) =
        LoginRepository(service, repo)
}