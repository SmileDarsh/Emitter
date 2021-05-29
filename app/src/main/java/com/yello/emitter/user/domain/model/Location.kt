package com.yello.emitter.user.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
@Parcelize
data class Location(val lat: String, val lng: String) : Parcelable
