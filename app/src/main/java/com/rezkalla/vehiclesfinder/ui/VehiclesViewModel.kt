package com.rezkalla.vehiclesfinder.ui

import androidx.lifecycle.ViewModel
import com.rezkalla.domain.usecase.GetCurrentVehiclesUseCase
import com.rezkalla.vehiclesfinder.mapper.VehicleMapper
import javax.inject.Inject

class VehiclesViewModel @Inject constructor(
    private val getCurrentVehiclesUseCase: GetCurrentVehiclesUseCase,
    private val mapper: VehicleMapper
) : ViewModel() {




}