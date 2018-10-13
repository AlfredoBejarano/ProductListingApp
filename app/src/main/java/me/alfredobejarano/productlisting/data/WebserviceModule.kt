package me.alfredobejarano.productlisting.data

import dagger.Module
import dagger.Provides
import me.alfredobejarano.productlisting.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 * Dagger module that defines how to inject
 * an implementation of the [Webservice] interface.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 09:45 PM
 * @version 1.0
 **/
@Module
class WebserviceModule {
    /**
     * Defines how a [Webservice] implementation must be provided when injected.
     * @return [Webservice] implementation for injection.
     */
    @Provides
    @Singleton
    fun provideWebservice(): Webservice {
        // Create a new OkHttpClient with a timeout of 10 seconds.
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
        // If the app is in debug mode, use a LoggingInterceptor.
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }
        // Create the retrofit object for the web service.
        val retrofit = Retrofit.Builder()
            .client(client.build())
            .baseUrl(BuildConfig.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Return the Webservice implementation created by Retrofit.
        return retrofit.create(Webservice::class.java)
    }
}