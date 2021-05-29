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
@Entity(tableName = "address")
data class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "addressId")
    var id: Int? = null,
    @ColumnInfo(name = "addressUserId")
    var userId: Int? = null,
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    @Ignore
    var geo: Location? = null,
) : Parcelable
