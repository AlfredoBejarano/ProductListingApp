package me.alfredobejarano.productlisting.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Entity class that defines the necessary values
 * for a user session as the expiration date and
 * access token.
 *
 * @author Alfredo Bejarano
 * @since 12/10/2018 - 07:59 PM
 * @version 1.0
 **/
@Entity(tableName = "sessions_table")
data class Session(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "primary_key")
    var id: String,
    var name: String,
    var token: String,
    var email: String,
    var image: String
)