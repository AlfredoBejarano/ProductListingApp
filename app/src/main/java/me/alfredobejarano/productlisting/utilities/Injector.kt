package me.alfredobejarano.productlisting.utilities

import android.app.Application
import me.alfredobejarano.productlisting.data.*

/**
 *
 * Utility class that provides injection fr the dependency graphs.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 06:28 PM
 * @version 1.0
 **/
object Injector {
    /**
     * Reference to this project application.
     */
    private lateinit var mApp: Application

    /**
     * Function that initializes this injector utility object.
     * @param application This module application class.
     */
    fun initialize(application: Application) {
        mApp = application
    }

    /**
     * Singleton access for the [Application component][DaggerAppComponent]
     * that allows injection for components in the app.
     */
    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .webserviceModule(WebserviceModule())
            .loginRepositoryModule(LoginRepositoryModule())
            .postRepositoryModule(PostRepositoryModule(mApp))
            .sessionRepositoryModule(SessionRepositoryModule(mApp))
            .build()
    }
}