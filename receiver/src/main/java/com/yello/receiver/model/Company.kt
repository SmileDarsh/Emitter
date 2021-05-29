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
@Entity(tableName = "company")
data class Company(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "companyId")
    var id: Int? = null,
    @ColumnInfo(name = "companyUserId")
    var userId: Int? = null,
    @ColumnInfo(name = "companyName")
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null
) : Parcelable
