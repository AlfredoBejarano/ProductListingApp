package me.alfredobejarano.productlisting.data

import androidx.room.Dao
import androidx.room.Query

/**
 *
 * Data access object class that defines the database
 * operations for the Session entities.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 09:04 PM
 * @version 1.0
 **/
@Dao
interface SessionDao {
    /**
     * Retrieves the only session object from the database.
     * @return List of found session objects.
     */
    @Query("SELECT * FROM sessions_table LIMIT 1")
    fun getCurrentSession(): List<Session>

    /**
     * Nukes the sessions table
     */
    @Query("DELETE FROM sessions_table")
    fun deleteSession()
}