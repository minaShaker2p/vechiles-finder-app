package com.rezkalla.remote.api

import com.rezkalla.remote.model.VehiclesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface VehiclesApiService {
    @GET("b/5fa8ff8dbd01877eecdb898f")
    fun getCurrentVehicles(): Single<VehiclesResponse>
}