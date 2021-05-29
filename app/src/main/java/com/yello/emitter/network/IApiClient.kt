package com.yello.emitter.network

import com.yello.emitter.user.domain.model.User
import retrofit2.http.*

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
interface IApiClient {
    @GET("users")
    suspend fun getUsers(): MutableList<User>
}