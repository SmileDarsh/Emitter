package com.yello.emitter.user.presentation.viewModel.state

import com.yello.emitter.user.domain.model.User

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
sealed class UserVS {
    class AddUser(val user: User) : UserVS()
    class Error(val message: String) : UserVS()
    object Empty : UserVS()
    object InternetError : UserVS()
}
