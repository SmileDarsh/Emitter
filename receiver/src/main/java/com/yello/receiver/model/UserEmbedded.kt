package com.yello.receiver.model

import androidx.room.Embedded
import com.yello.receiver.model.Address
import com.yello.receiver.model.Company
import com.yello.receiver.model.Location
import com.yello.receiver.model.User

/**
 * Created by µðšţãƒâ ™ on 29/05/2021.
 *  ->
 */
data class UserEmbedded(
    @Embedded val user: User,
    @Embedded val address: Address,
    @Embedded val location: Location,
    @Embedded val company: Company
)
