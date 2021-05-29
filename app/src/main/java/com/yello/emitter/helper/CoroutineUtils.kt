package com.yello.emitter.helper

import kotlinx.coroutines.*

suspend fun io(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.IO) {
        block.invoke(this)
    }
}

suspend fun ui(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.Main) {
        block.invoke(this)
    }
}

fun scopeDelay(time: Long = 0L, block: suspend CoroutineScope.() -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        delay(time)
        withContext(Dispatchers.Main) {
            block.invoke(this)
        }
    }
}