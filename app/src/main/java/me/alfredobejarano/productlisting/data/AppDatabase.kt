package me.alfredobejarano.productlisting.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.alfredobejarano.productlisting.BuildConfig

/**
 *
 * Class defining all the accessors for the app database entities.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 08:53 PM
 * @version 1.0
 **/
@Database(entities = [Session::class, Post::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Retrieves a reference of [SessionDao] to
     * access database operations of the [Session] entity.
     * @return Reference to the [SessionDao].
     */
    abstract fun getSessionDao(): SessionDao

    /**
     * Retrieves an implementation of [PostDao],
     * allowing access to the database operations.
     */
    abstract fun getPostDao(): PostDao
    companion object {
        // Used for singleton initialization
        @Volatile
        private var instance: AppDatabase? = null

        /**
         * Retrieves the instance of the app database.
         * @param ctx The context requesting the app database.
         */
        fun getInstance(ctx: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(ctx).also { instance = it }
        }

        /**
         * Builds an instance of the app database using Room.
         * @param ctx The context building the database instance.
         */
        private fun buildDatabase(ctx: Context) =
            Room.databaseBuilder(
                ctx,
                AppDatabase::class.java,
                "{${BuildConfig.APPLICATION_ID}-db}"
            ).fallbackToDestructiveMigration().build()
    }
}