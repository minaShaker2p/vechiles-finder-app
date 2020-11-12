package com.rezkalla.vehiclesfinder.mapper

import com.rezkalla.domain.entity.VehicleEntity
import com.rezkalla.vehiclesfinder.model.Vehicle
import javax.inject.Inject

class VehicleMapper @Inject constructor() : Mapper<VehicleEntity, Vehicle> {
    override fun from(e: Vehicle): VehicleEntity {
        return VehicleEntity()
    }

    override fun to(t: VehicleEntity): Vehicle {
        return Vehicle(
            t.vehicleId,
            t.zoneId,
            t.resolvedAt,
            t.battery,
            t.state,
            t.latitude,
            t.longitude
        )
    }
}