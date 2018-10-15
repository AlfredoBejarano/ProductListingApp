package me.alfredobejarano.productlisting.data

import dagger.Component
import me.alfredobejarano.productlisting.LoginFragment
import me.alfredobejarano.productlisting.PostsFragment
import me.alfredobejarano.productlisting.SplashFragment
import me.alfredobejarano.productlisting.viewmodel.ViewModelFactoryModule
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
        PostRepositoryModule::class,
        LoginRepositoryModule::class,
        ViewModelFactoryModule::class,
        SessionRepositoryModule::class]
)
interface AppComponent {
    /**
     * Allows injection for a [SplashFragment] class.
     */
    fun inject(splashFragment: SplashFragment)

    /**
     * Allows injection for a [LoginFragment] class.
     */
    fun inject(loginFragment: LoginFragment)

    /**
     * Allows injection for a [PostsFragment] class.
     */
    fun inject(postsFragment: PostsFragment)


    /**
     * Provides injection for a [Webservice] implementation.
     */
    fun provideWebservice(): Webservice

    /**
     * Provides injection for [SessionDao] class.
     */
    fun provideSessionDao(): SessionDao

    /**
     * Provides injection for [PostDao] class.
     */
    fun providePostDao(): PostDao

    /**
     * Provides injection for the [LoginRepository] class.
     */
    fun provideLoginRepository(): LoginRepository

    /**
     * Provides injection for the [PostRepository] class.
     */
    fun providePostRepository(): PostRepository

    /**
     * Provides injection for a [SessionRepository] class.
     */
    fun provideSessionRepository(): SessionRepository
}