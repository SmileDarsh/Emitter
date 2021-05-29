package com.yello.receiver.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userId")
    var id: Int? = null,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var website: String? = null,
    @Ignore
    var address: Address? = null,
    @Ignore
    var company: Company? = null
) : Parcelable {

    fun addUser(userRoom: UserEmbedded) {
        this.id = userRoom.user.id
        this.name = userRoom.user.name
        this.username = userRoom.user.username
        this.email = userRoom.user.email
        this.phone = userRoom.user.phone
        this.website = userRoom.user.website
        this.address = userRoom.address
        this.address?.geo = userRoom.location
        this.company = userRoom.company
    }
}