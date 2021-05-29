package com.yello.emitter.user.data.repository

import com.yello.emitter.user.data.rest.UserRestDataStore
import com.yello.emitter.user.domain.model.User
import com.yello.emitter.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class UserRepositoryImpl(private val mUserRestDataStore: UserRestDataStore) : UserRepository {

    override fun getUsers(): Flow<User> = mUserRestDataStore.getOrders()
}