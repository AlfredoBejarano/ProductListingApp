package me.alfredobejarano.productlisting.data

import org.junit.Test
import org.mockito.Mockito

/**
 *
 * Test class for the [SessionDao] interface.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 08:32 PM
 * @version 1.0
 **/
class SessionDaoTest : BaseDaoTest() {
    private val dao = mockDatabase.getSessionDao()

    /**
     * Asserts that a session gets inserted correctly in the local db.
     */
    @Test
    fun insertSession_SaveSession() {
        // Mock a session object.
        val session = Mockito.mock(Session::class.java)
        // Persist the mock session.
        dao.persistSession(session)
        // Now, verify that the session was stored.
        assert(dao.getCurrentSession().value?.isNotEmpty() == true)
    }

    /**
     * Asserts that an inserted session gets deleted.
     */
    @Test
    fun insertSession_DeleteSession() {
        // Mock a session object
        val session = Mockito.mock(Session::class.java)
        // Persist the session
        dao.persistSession(session)
        // Now, delete the session.
        dao.deleteSession()
        // Now, verify that the session was deleted
        assert(dao.getCurrentSession().value?.isEmpty() == true)
    }
}