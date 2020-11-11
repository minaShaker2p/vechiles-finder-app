package com.rezkalla.data.mapper

import com.rezkalla.data.model.VehicleData
import com.rezkalla.domain.entity.VehicleEntity
import javax.inject.Inject

class VehicleDataMapper @Inject constructor() : Mapper<VehicleEntity, VehicleData> {
    override fun from(e: VehicleData): VehicleEntity {
        return VehicleEntity(
            e.id,
            e.vehicleId,
            e.hardwareId,
            e.zoneId,
            e.resolution,
            e.resolvedBy,
            e.resolvedAt,
            e.battery,
            e.state,
            e.model,
            e.fleetBirdId,
            e.latitude,
            e.longitude
        )
    }

    override fun to(t: VehicleEntity): VehicleData {
        return VehicleData(
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