package com.yello.emitter.network

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
interface Interactor<in Params, out Type> {

    fun execute(params: Params): Type

    object None
}