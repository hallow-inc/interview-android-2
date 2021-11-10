package com.hallow.interview.utilities

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> throttleFirst(
    scope: CoroutineScope,
    skipMs: Long = 300L,
    callback: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    return { param: T ->
        if (throttleJob?.isCompleted != false) {
            throttleJob = scope.launch {
                callback.invoke(param)
                delay(skipMs)
            }
        }
    }
}

fun throttleFirst(
    scope: CoroutineScope,
    skipMs: Long = 300L,
    callback: () -> Unit
): () -> Unit {
    var throttleJob: Job? = null
    return {
        if (throttleJob?.isCompleted != false) {
            throttleJob = scope.launch {
                callback.invoke()
                delay(skipMs)
            }
        }
    }
}

fun <T> debounce(
    scope: CoroutineScope,
    delayMs: Long = 300L,
    callback: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob?.isCompleted != false) {
            debounceJob = scope.launch {
                delay(delayMs)
                callback(param)
            }
        }
    }
}

fun debounce(
    scope: CoroutineScope,
    delayMs: Long = 300L,
    callback: () -> Unit
): () -> Unit {
    var debounceJob: Job? = null
    return {
        if (debounceJob?.isCompleted != false) {
            debounceJob = scope.launch {
                delay(delayMs)
                callback()
            }
        }
    }
}
