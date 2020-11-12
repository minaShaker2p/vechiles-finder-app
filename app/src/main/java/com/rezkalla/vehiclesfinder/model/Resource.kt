package com.rezkalla.vehiclesfinder.model

data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = null) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}