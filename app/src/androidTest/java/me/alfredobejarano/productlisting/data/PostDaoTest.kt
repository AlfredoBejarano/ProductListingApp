package me.alfredobejarano.productlisting.data

import org.junit.Test

/**
 *
 * Test class for the [PostDao] class.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 08:32 PM
 * @version 1.0
 **/
class PostDaoTest : BaseDaoTest() {
    private val dao = mockDatabase.getPostDao()

    /**
     * Asserts that a post gets inserted correctly in the local db.
     */
    @Test
    fun insertPost_SavePost() {
        // Mock a session object.
        val post = Post.createMock()
        // Persist the mock post.
        dao.insertOrUpdate(post)
        // Now, verify that the post was saved.
        assert(dao.read().value?.isNotEmpty() == true)
    }

    /**
     * Asserts that an inserted session gets deleted.
     */
    @Test
    fun insertPost_DeletePost() {
        // Mock a session object
        val post = Post.createMock()
        // Persist the session
        dao.insertOrUpdate(post)
        // Now, delete the session.
        dao.deleteAll()
        // Now, verify that the session was deleted
        assert(dao.read().value?.isEmpty() == true)
    }

    /**
     * Asserts that a session gets inserted correctly.
     */
    @Test
    fun insertPost_ReadPost() {
        // Create a new post with a known id.
        val post = Post.createMock()
        // Insert the post to the database.
        dao.insertOrUpdate(post)
        // Assert that the fetched post id is the same as the post stored.
        assert(post.id == dao.read(1).value?.id)
    }

    /**
     * Inserts a set of posts and reads that the
     * amount of posts is the same as the inserted one.
     */
    @Test
    fun insertPosts_ReadPosts() {
        // Add 5 post mocks to the database.
        repeat(5) {
            dao.insertOrUpdate(Post.createMock())
        }
        // Assert that the posts in the table are 5.
        assert(dao.read().value?.size == 5)
    }
}