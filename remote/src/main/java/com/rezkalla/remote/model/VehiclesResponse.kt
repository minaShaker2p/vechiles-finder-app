package com.rezkalla.remote.model

import com.google.gson.annotations.SerializedName

data class VehiclesResponse(
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("current")
    val vehicles: List<VehicleNetwork>,
    @SerializedName("stats")
    val states: Stats
)

data class Stats(
    @SerializedName("open")
    val open: Int,
    @SerializedName("assigned")
    val assigned: Int,
    @SerializedName("resolved")
    val resolved: Int
)