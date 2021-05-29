package com.yello.emitter.user.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yello.emitter.helper.io
import com.yello.emitter.helper.ui
import com.yello.emitter.network.Interactor
import com.yello.emitter.user.domain.interactor.GetUsersInteractor
import com.yello.emitter.user.presentation.viewModel.state.UserVS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class UserViewModel(private val mGetUsersInteractor: GetUsersInteractor) : ViewModel(),
    KoinComponent {

    val viewState: LiveData<UserVS> get() = mViewState
    private val mViewState = MutableLiveData<UserVS>()

    fun users() {
        viewModelScope.launch {
            try {
                io {
                    mGetUsersInteractor.execute(Interactor.None).collect {
                        ui {
                            mViewState.value = UserVS.AddUser(it)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}