package me.alfredobejarano.productlisting.data

import androidx.lifecycle.MutableLiveData
import me.alfredobejarano.productlisting.utilities.runOnIOThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.util.*
import javax.inject.Inject

/**
 *
 * Repository class that handles the source of the [Post] model class.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 05:29 PM
 * @version 1.0
 **/
class PostRepository @Inject constructor(
    private val ws: Webservice,
    private val dao: PostDao
) {
    /**
     * Reference to the list of post fetched from the db or the cloud.
     */
    val results = MutableLiveData<List<Post>>()
    /**
     * [Callback] object that responds to a request for fetching
     * a list of [Post] from the server.
     */
    private val postListCallback = object : Callback<Wrapper<PostsList>> {
        /**
         * Reports to the results property that no results were found.
         */
        override fun onFailure(call: Call<Wrapper<PostsList>>, t: Throwable) = results.postValue(null)

        /**
         * Informs to the results property the value of the fetched
         * posts list from the cloud.
         */
        override fun onResponse(c: Call<Wrapper<PostsList>>, r: Response<Wrapper<PostsList>>) =
            r.body()?.data?.let {
                runOnIOThread {
                    it.results.forEach {
                        dao.insertOrUpdate(it)
                    }
                    results.postValue(it.results)
                }
            } ?: run {
                onFailure(c, HttpException(r))
            }
    }

    /**
     * Reads a post from the cached in the database using
     * an [Integer] as the primary key.
     * @param postId The primary key of the post.
     * @return [Post] object retrieved from the db.
     */
    fun fetchPost(postId: Int) = dao.read(postId)

    /**
     * Retrieve a list of posts and report the results to
     * a [MutableLiveData] property for observation.
     */
    fun fetchPosts() {
        // Read the posts from the local db.
        val posts = dao.read().value
        // Check if the cached posts list is not null and if the cache is still fresh.
        if (posts?.isNotEmpty() == false
            && isCacheFresh(posts.first().cachedSince)
        ) {
            // If it is, notify the cached posts.
            results.postValue(posts)
        } else {
            // If not, retrieve them from the server.
            ws.fetchPosts().enqueue(postListCallback)
        }
    }

    /**
     * Receives a cached time from a [Post] object
     * and checks if its cache is still fresh.
     * @param millis The cache time from the [Post] object.
     */
    private fun isCacheFresh(millis: Long): Boolean {
        // Retrieve the system calendar instance.
        val calendar = Calendar.getInstance()
        // Set the calendar time as the Post object cache date.
        calendar.timeInMillis = millis
        // Add ten minutes to the post cache time.
        calendar.add(Calendar.MINUTE, 10)
        // Return if the current system time is before the cached
        // time + 10 minutes., if not, the cache is not fresh anymore.
        return Date().before(calendar.time)
    }
}