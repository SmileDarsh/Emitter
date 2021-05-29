package com.yello.emitter.user.data.rest

import com.yello.emitter.network.RetrofitApi
import com.yello.emitter.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class UserRestDataStore {

    fun getOrders(): Flow<User> = flow {
        RetrofitApi.instance.getUsers().forEach { emit(it) }
    }
}