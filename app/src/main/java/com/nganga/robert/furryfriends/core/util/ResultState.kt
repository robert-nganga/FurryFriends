package com.nganga.robert.furryfriends.core.util

class ResultState<T>(val status: Status, val data: T?, val message: String?) {
    enum class Status { SUCCESS, ERROR, LOADING }

    companion object{
        fun <T> success(data: T?): ResultState<T> {
            return ResultState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T? = null): ResultState<T> {
            return ResultState(Status.ERROR, data, msg)
        }

        fun <T> loading(): ResultState<T> {
            return ResultState(Status.LOADING, null, null)
        }
    }
}