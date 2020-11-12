package com.rezkalla.remote.mapper

import com.rezkalla.data.model.VehicleData
import com.rezkalla.remote.model.VehicleNetwork
import javax.inject.Inject

class VehiclesDataNetworkMapper @Inject constructor() : Mapper<VehicleData, VehicleNetwork> {
    override fun from(e: VehicleNetwork): VehicleData {
        return VehicleData(
            e.id,
            e.vehicleId,
            e.hardwareId,
            e.zoneId,
            e.resolution ?: "",
            e.resolvedBy ?: "",
            e.resolvedAt ?: "",
            e.battery,
            e.state,
            e.model,
            e.fleetBirdId,
            e.latitude,
            e.longitude
        )
    }

    override fun to(t: VehicleData): VehicleNetwork {
        return VehicleNetwork(
            t.id,
            t.vehicleId,
            t.hardwareId,
            t.zoneId,
            t.resolution,
            t.resolvedBy,
            t.resolvedAt,
            t.battery,
            t.state,
            t.model,
            t.fleetBirdId,
            t.latitude,
            t.longitude
        )
    }

}