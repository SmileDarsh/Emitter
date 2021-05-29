package com.yello.emitter.user.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
@Parcelize
data class Company(val name: String, val catchPhrase: String, val bs: String) : Parcelable
