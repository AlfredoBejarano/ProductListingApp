package me.alfredobejarano.productlisting.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 *
 * Interface to be implemented by Room.
 * This class defines all the database operations
 * for the [Post] entity.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 05:15 PM
 * @version 1.0
 **/
@Dao
interface PostDao {
    /**
     * Inserts a [Post] object into the local storage database,
     * If a [Post] object already exists, it gets replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(post: Post)

    /**
     * Retrieves all the cached [Post] objects from the local db.
     * @return [LiveData] object wrapping a  [List] of [Post] for observation.
     */
    @Query("SELECT * FROM posts_table ORDER BY inserted_at ASC")
    fun read(): LiveData<List<Post>>

    /**
     * Retrieves a single [Post] object from the db.
     * @param pk The [Post] object primary key.
     * @return [LiveData] object wrapping the [Post] object, allowing observation.
     */
    @Query("SELECT * FROM posts_table WHERE pk = :pk")
    fun read(pk: Int): LiveData<Post>

    /**
     * Removes all the cached [Post] objects from the database.
     */
    @Query("DELETE FROM posts_table")
    fun deleteAll()
}