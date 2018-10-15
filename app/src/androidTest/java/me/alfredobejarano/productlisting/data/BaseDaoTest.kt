package me.alfredobejarano.productlisting.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

/**
 *
 * Base class for testing Dao classes.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 08:24 PM
 * @version 1.0
 **/
@RunWith(AndroidJUnit4::class)
open class BaseDaoTest {
    @Rule
    @JvmField
    val instantExecutionRule = InstantTaskExecutorRule()

    /**
     * Reference to a in-memory database for testing.
     */
    protected val mockDatabase: AppDatabase = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().context,
        AppDatabase::class.java
    ).build()
}