package com.yello.emitter.user.domain.interactor

import com.yello.emitter.network.Interactor
import com.yello.emitter.user.domain.model.User
import com.yello.emitter.user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class GetUsersInteractor(private val mUserRepository: UserRepository) :
    Interactor<Interactor.None, Flow<User>> {
    override fun execute(params: Interactor.None): Flow<User> = mUserRepository.getUsers()
}