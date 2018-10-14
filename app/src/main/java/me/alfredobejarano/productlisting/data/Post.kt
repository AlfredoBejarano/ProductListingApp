package me.alfredobejarano.productlisting.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 *
 * Model class that defines the JSON structure
 * of a single post, it implements [Parcelable]
 * as a medium for sending it as an extra argument.
 *
 * @author Alfredo Bejarano
 * @since 14/10/2018 - 01:05 AM
 * @version 1.0
 **/
@Entity(tableName = "posts_table")
data class Post(
    @Expose
    @ColumnInfo(name = "pk")
    @SerializedName("idPost")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Expose
    @SerializedName("body")
    val body: String,
    @Expose
    @SerializedName("")
    val slug: String,
    @Expose
    @SerializedName("title")
    val title: Int,
    @Expose
    @SerializedName("header")
    val header: String,
    @Expose
    @SerializedName("footer")
    val footer: String,
    @Expose
    @SerializedName("updated_at")
    val updateTimestamp: String,
    @Expose
    @SerializedName("created_at")
    var creationTimestamp: String
) {
    /**
     * Property that defines since when this post object has been cached.
     */
    @ColumnInfo(name = "inserted_at")
    var cachedSince: Long = Calendar.getInstance().timeInMillis
}