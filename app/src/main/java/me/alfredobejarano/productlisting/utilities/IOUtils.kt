package me.alfredobejarano.productlisting.utilities

import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

/**
 *
 * Utils class that provides functions to handle
 * work on different threads easily
 *
 * @author Alfredo Bejarano
 * @since 13/10/2018 - 12:44 AM
 * @version 1.0
 **/
/**
 * Single threaded executor that allows job being done in a worker thread.
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Extension function for the [ViewModel] class that allows
 * executing a function in a worker thread.
 */
fun runOnIOThread(f: () -> Unit) = IO_EXECUTOR.execute(f)