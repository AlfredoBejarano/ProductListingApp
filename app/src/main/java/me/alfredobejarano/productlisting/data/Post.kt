package me.alfredobejarano.productlisting.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
data class Post(
    @Expose
    @SerializedName("idPost")
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(body)
        parcel.writeString(slug)
        parcel.writeInt(title)
        parcel.writeString(header)
        parcel.writeString(footer)
        parcel.writeString(updateTimestamp)
        parcel.writeString(creationTimestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}