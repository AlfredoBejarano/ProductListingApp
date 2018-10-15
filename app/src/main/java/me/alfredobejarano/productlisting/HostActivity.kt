package me.alfredobejarano.productlisting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController

/**
 * Simple [AppCompatActivity] subclass that serves as the
 * host for all the destinations of the app using the new
 * android jetpack navigation component.
 */
class HostActivity : AppCompatActivity() {
    /**
     * Create this activity and set its layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
    }

    /**
     * Override this function to provide upwards navigation.
     */
    override fun onSupportNavigateUp() =
        findNavController(this, R.id.nav_host_fragment)
            .navigateUp()
}
