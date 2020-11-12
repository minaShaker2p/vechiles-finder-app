package com.rezkalla.vehiclesfinder.di

import com.rezkalla.data.model.VehicleData
import com.rezkalla.data.source.RemoteDataSource
import com.rezkalla.remote.mapper.Mapper
import com.rezkalla.remote.mapper.VehiclesDataNetworkMapper
import com.rezkalla.remote.model.VehicleNetwork
import com.rezkalla.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteModule {

    @Binds
    abstract fun bindsRemoteSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    abstract fun bindVehiclesDataNetworkMapper(
        userInfoMapper: VehiclesDataNetworkMapper
    ): Mapper<VehicleData, VehicleNetwork>

}