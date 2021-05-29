package com.yello.receiver.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
@Parcelize
@Entity(tableName = "location")
data class Location(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "locationId")
    var id: Int? = null,
    @ColumnInfo(name = "geoAddressId")
    var addressId: Int? = null,
    var lat: String? = null,
    var lng: String? = null
) : Parcelable
