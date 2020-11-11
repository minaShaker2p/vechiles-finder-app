package com.rezkalla.domain.usecase

import com.rezkalla.domain.entity.VehicleEntity
import com.rezkalla.domain.qualifier.Background
import com.rezkalla.domain.qualifier.Foreground
import com.rezkalla.domain.repository.CurrentVehiclesRepository
import com.rezkalla.domain.usecase.base.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetCurrentVehiclesUseCase @Inject constructor(
    private val vehiclesRepository: CurrentVehiclesRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : SingleUseCase<List<VehicleEntity>, Unit>(backgroundScheduler, foregroundScheduler) {

    override fun build(input: Unit?): Single<List<VehicleEntity>> {
        return vehiclesRepository.getCurrentVehicles()
    }
}