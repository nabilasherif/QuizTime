package com.example.domain.util

sealed class Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>()
    data class Failure<E : Error>(val error: E) : Result<Nothing, E>()
}

suspend inline fun <T, E : Error> Result<T, E>.onSuccess(crossinline action: suspend (T) -> Unit): Result<T, E> {
    if (this is Result.Success) action(data)
    return this
}

suspend inline fun <T, E : Error> Result<T, E>.onFailure(crossinline action: suspend (E) -> Unit): Result<T, E> {
    if (this is Result.Failure) action(error)
    return this
}