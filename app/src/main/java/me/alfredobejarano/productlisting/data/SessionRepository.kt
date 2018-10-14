package me.alfredobejarano.productlisting.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
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
class SessionRepository @Inject constructor(
    private val dao: SessionDao,
    private val webservice: Webservice
) {
    /**
     * Reference to the token performing the profile fetching.
     */
    private var token: String = ""

    /**
     * Callback object that fetches a user profile from the cloud.
     */
    private val profileCallback = object : Callback<Wrapper<User>> {
        /**
         * Reports that fetching a profile was not possible.
         */
        override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) = sessionFetched.postValue(false)

        /**
         * Stores the retrieved user profile as a session in the local storage databse.
         */
        override fun onResponse(c: Call<Wrapper<User>>, r: Response<Wrapper<User>>) = r.body()?.data?.let {
            dao.persistSession(
                Session(it.id, "${it.name} ${it.surName}", token, it.email, it.image)
            )
            sessionFetched.postValue(true)
        } ?: run {
            onFailure(c, HttpException(r))
        }
    }
    /**
     * [MutableLiveData] object that provides observation to the UI controllers
     * about the status of fetching and storing a user session in the device.
     */
    var sessionFetched: MutableLiveData<Boolean> = MutableLiveData()
    /**
     * [LiveData] object that provides observation to the UI controllers
     * about the stored session in the device local database.
     */
    var currentSession: LiveData<List<Session>> = MutableLiveData()

    /**
     * Fetches the current session from the database.
     * @return [LiveData] object containing the [Session] object within a [List].
     */
    fun retrieveSession() {
        currentSession = dao.getCurrentSession()
    }

    /**
     * Deletes the current session from the database.
     */
    fun deleteSession() = dao.deleteSession()

    /**
     * Stores a session from a response.
     * @param session The session to be persisted.
     */
    private fun persistSession(session: Session) = dao.persistSession(session)

    /**
     * Uses an [access token][String] to retrieve the details of a User.
     * @param accessToken The token for the user, retrieved via login.
     * @see LoginRepository
     */
    fun fetchUserFromToken(accessToken: String) {
        token = accessToken
        webservice.fetchUserProfile(accessToken).enqueue(profileCallback)
    }
}