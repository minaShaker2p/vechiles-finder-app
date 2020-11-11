package com.rezkalla.data.source

import com.rezkalla.data.model.VehicleData
import io.reactivex.Single

interface RemoteDataSource {

    fun getCurrentVehicles():Single<List<VehicleData>>
}