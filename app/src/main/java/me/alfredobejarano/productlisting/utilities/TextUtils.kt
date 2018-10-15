package me.alfredobejarano.productlisting.utilities

import android.os.Build
import android.text.Html

/**
 *
 * Utils class for String classes.
 *
 * @author Alfredo Bejarano
 * @since 15/10/2018 - 01:20 PM
 * @version 1.0
 **/
/**
 * Parses a given string from HTML into Text.
 */
fun String.fromHTML(): String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
} else {
    Html.fromHtml(this).toString()
}