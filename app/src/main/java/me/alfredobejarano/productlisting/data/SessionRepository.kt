package me.alfredobejarano.productlisting.data

import javax.inject.Inject

/**
 *
 * Repository class that serves as the single source
 * of truth of the Session entity.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 10:02 PM
 * @version 1.0
 **/
class SessionRepository @Inject constructor(private val dao: SessionDao) {
    /**
     * Fetches the current session from the database.
     * @return [androidx.lifecycle.LiveData] object containing the [Session] object.
     */
    fun retrieveSession() = dao.getCurrentSession()

    /**
     * Deletes the current session from the database.
     */
    fun deleteSession() = dao.deleteSession()

    /**
     * Stores a session from a response.
     * @param response The valid response object from the server.
     */
    fun persistSession(session: Session) = dao.persistSession(session)
}