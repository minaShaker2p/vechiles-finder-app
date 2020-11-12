package com.rezkalla.vehiclesfinder.mapper

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}