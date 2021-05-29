package com.yello.emitter.di

import com.yello.emitter.user.data.repository.UserRepositoryImpl
import com.yello.emitter.user.data.rest.UserRestDataStore
import com.yello.emitter.user.domain.interactor.GetUsersInteractor
import com.yello.emitter.user.domain.repository.UserRepository
import com.yello.emitter.user.presentation.viewModel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val userModule = module {
    viewModel { UserViewModel(get()) }
    single { GetUsersInteractor(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { UserRestDataStore() }
}

val modules = listOf(userModule)