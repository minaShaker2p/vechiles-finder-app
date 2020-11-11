package com.rezkalla.domain.repository

import com.rezkalla.domain.entity.VehicleEntity
import io.reactivex.Single

interface CurrentVehiclesRepository {

    fun getCurrentVehicles(): Single<List<VehicleEntity>>
}