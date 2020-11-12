package com.rezkalla.vehiclesfinder.di

import com.rezkalla.data.mapper.Mapper
import com.rezkalla.data.mapper.VehicleDataMapper
import com.rezkalla.data.model.VehicleData
import com.rezkalla.data.repository.CurrentVehicleRepositoryImpl
import com.rezkalla.domain.entity.VehicleEntity
import com.rezkalla.domain.repository.CurrentVehiclesRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: CurrentVehicleRepositoryImpl
    ): CurrentVehiclesRepository

    @Binds
    abstract fun bindsVehicleDataMapper(
        mapper: VehicleDataMapper
    ): Mapper<VehicleEntity, VehicleData>
}
