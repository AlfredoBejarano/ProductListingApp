package me.alfredobejarano.productlisting.viewmodel

import dagger.Module
import dagger.Provides
import me.alfredobejarano.productlisting.utilities.Injector
import javax.inject.Singleton

/**
 *
 * Module for dagger that provides injection
 * for the different factory classes for the
 * application [ViewModel] subclasses.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 06:46 PM
 * @version 1.0
 **/
@Module
class ViewModelFactoryModule {
    /**
     * Provides injection for the [SessionViewModel.Factory] class.
     */
    @Provides
    @Singleton
    fun provideSessionViewModelFactory() =
        SessionViewModel.Factory(Injector.component.provideSessionRepository())

    /**
     * Provides injection for the [LoginViewModel.Factory] class.
     */
    @Provides
    @Singleton
    fun provideLoginViewModelFactory() =
        LoginViewModel.Factory(Injector.component.provideLoginRepository())

    /**
     * Provides injection for the [PostViewModel.Factory] class.
     */
    @Provides
    @Singleton
    fun providePostViewModelFactory() =
        PostViewModel.Factory(Injector.component.providePostRepository())
}