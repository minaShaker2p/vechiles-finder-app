package com.rezkalla.remote.source

import com.rezkalla.data.model.VehicleData
import com.rezkalla.data.source.RemoteDataSource
import com.rezkalla.remote.api.VehiclesApiService
import com.rezkalla.remote.mapper.VehiclesDataNetworkMapper
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val vehiclesApiService: VehiclesApiService,
    private val mapper: VehiclesDataNetworkMapper
) : RemoteDataSource {
    override fun getCurrentVehicles(): Single<List<VehicleData>> {
        return vehiclesApiService.getCurrentVehicles().map {
            it.data.vehicles.map { vehicle ->
                mapper.from(vehicle)
            }
        }
    }
}