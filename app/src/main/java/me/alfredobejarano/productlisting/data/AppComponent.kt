package me.alfredobejarano.productlisting.data

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.Component
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
     * Allows injection for a fragment subclass.
     */
    fun inject(fragment: Fragment)

    /**
     * Allows injection for a ViewModel subclass
     */
    fun inject(viewModel: ViewModel)

    /**
     * Allows injection for a Activity subclass.
     */
    fun inject(activity: AppCompatActivity)

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