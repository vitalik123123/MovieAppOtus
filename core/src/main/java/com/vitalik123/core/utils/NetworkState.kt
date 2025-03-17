package com.vitalik123.core.utils

sealed interface NetworkState<T> {
    data class Error<T>(val error: Throwable): NetworkState<T>
    data class ServerError<T>(val error: Throwable): NetworkState<T>
    data class Success<T>(val data: T): NetworkState<T>
}