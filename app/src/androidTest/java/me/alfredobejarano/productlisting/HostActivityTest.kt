package me.alfredobejarano.productlisting

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import me.alfredobejarano.productlisting.adapter.PostsAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test class for [HostActivity].
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 15/10/2018 - 01:29 AM
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class HostActivityTest {
    @Rule
    @JvmField
    var mTestRule: ActivityTestRule<HostActivity> =
        ActivityTestRule(HostActivity::class.java)

    private lateinit var correctUsername: String
    private lateinit var correctPassword: String

    /**
     * Initializes the valid credentials fields for this test.
     */
    @Before
    fun initValidLogin() {
        correctUsername = "android@kokonutstudio.com"
        correctPassword = "12345678"
    }

    /**
     * Deletes the local database before running any test.
     */
    @Before
    fun clearDatabase() =
        InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .deleteDatabase("{${BuildConfig.APPLICATION_ID}-db}")

    /**
     * Tests the basic flow for the app, that is:
     * - Asserting a session exists.
     * - A session doesn't exists, so perform a login.
     * - After logging in, scroll through the list of posts and choose one.
     * - Assert that the post gets displayed.
     * - Scroll through the post contents.
     */
    @Test
    fun testAppFlow() {
        // Proceed to test the login actions
        typeCredentials_performValidLoginTest()
        // Wait for the network request to pass.
        Thread.sleep(1000)
        // After that, proceed to test the list of posts.
        scrollOnList_selectAnElementTest()
        // After selecting a post, assert it gets displayed.
        assertPostGetsDisplayed_scrollOnContentsTest()
    }

    /**
     * Checks that the post view content gets displayed and swipes
     * down and up the view.
     */
    private fun assertPostGetsDisplayed_scrollOnContentsTest() {
        // Check that the post contents get displayed.
        onView(withId(R.id.content))
            .check(matches(isDisplayed()))
        // Swipe through the vie contents.
        onView(withId(R.id.content))
            .perform(swipeDown())
        onView(withId(R.id.content))
            .perform(swipeUp())
    }

    /**
     * Scrolls through the list of posts and clicks the third result.
     */
    private fun scrollOnList_selectAnElementTest() {
        // Swipe down on the list 4 times.
        repeat(4) {
            onView(withId(R.id.postsList))
                .perform(swipeDown())
        }
        // Then swipe up on the list 4 times.
        repeat(4) {
            onView(withId(R.id.postsList))
                .perform(swipeUp())
        }
        // Finally, select the third element on the list.
        onView(withId(R.id.postsList))
            .perform(
                actionOnItemAtPosition<PostsAdapter.PostViewHolder>
                    (2, click())
            )
    }

    /**
     * Types the correct credentials into the login views
     * and then proceeds to perform a request.
     */
    private fun typeCredentials_performValidLoginTest() {
        // Type the username in the username view.
        typeOnView(R.id.username_input, correctUsername)
        // Type the password in the password view.
        typeOnView(R.id.password_input, correctPassword)
        // Click the login button to perform the request.
        onView(withId(R.id.login_button))
            .perform(click())
    }

    /**
     * Types a given text in a given view by its id.
     * @param viewId Id of the view to type on.
     * @param text The text to be typed on the view.
     */
    private fun typeOnView(viewId: Int, text: String) {
        // Retrieve the input field.
        onView(withId(viewId))
            // Type the correct password on it.
            .perform(typeText(text))
        // Close the soft keyboard after typing on it.
        closeSoftKeyboard()
    }
}