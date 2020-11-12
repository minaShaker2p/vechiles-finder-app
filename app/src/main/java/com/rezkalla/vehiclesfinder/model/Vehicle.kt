package com.rezkalla.vehiclesfinder.model

data class Vehicle(
    val vehicleId: String,
    val zoneId: String,
    val resolvedAt: String,
    val battery: Int,
    val state: String,
    val latitude: Double,
    val longitude: Double
)
