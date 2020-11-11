package com.rezkalla.data.repository

import com.rezkalla.data.mapper.VehicleDataMapper
import com.rezkalla.data.source.RemoteDataSource
import com.rezkalla.domain.entity.VehicleEntity
import com.rezkalla.domain.repository.CurrentVehiclesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 *  is the implementation for Current Vehicle Repository in Domain layer
 *  so our repository is our SSOT (Single source of truth ),
 *  so in the future it is so easy to add offline feature in our app
 */
class CurrentVehicleRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val vehicleDataMapper: VehicleDataMapper
) : CurrentVehiclesRepository {

    override fun getCurrentVehicles(): Single<List<VehicleEntity>> {
        return remoteDataSource.getCurrentVehicles().map { vehicles ->
            vehicles.map { vehicleDataMapper.from(it) }
        }
    }
}