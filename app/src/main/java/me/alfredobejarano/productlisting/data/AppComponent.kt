package me.alfredobejarano.productlisting.data

import dagger.Component
import javax.inject.Singleton

/**
 *
 * Dagger component that defines the app injection methods.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 09:27 PM
 * @version 1.0
 **/
@Singleton
@Component(
    modules = [
        WebserviceModule::class,
        LoginRepositoryModule::class,
        SessionRepositoryModule::class]
)
interface AppComponent {
    /**
     * Provides injection for a [Webservice] implementation.
     */
    fun provideWebservice(): Webservice

    /**
     * Provides injection for [SessionDao] class.
     */
    fun provideSessionDao(): SessionDao

    /**
     * Provides injection for the [LoginRepository] class.
     */
    fun provideLoginRepository(): LoginRepository
}