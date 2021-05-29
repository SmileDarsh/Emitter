package com.yello.emitter.user.domain.repository

import com.yello.emitter.user.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
interface UserRepository {
    fun getUsers(): Flow<User>
}