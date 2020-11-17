package com.rezkalla.vehiclesfinder.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rezkalla.domain.usecase.GetCurrentVehiclesUseCase
import com.rezkalla.vehiclesfinder.mapper.VehicleMapper
import com.rezkalla.vehiclesfinder.model.Resource
import com.rezkalla.vehiclesfinder.model.Vehicle
import javax.inject.Inject

class VehiclesViewModel @Inject constructor(
    private val getCurrentVehiclesUseCase: GetCurrentVehiclesUseCase,
    private val mapper: VehicleMapper
) : ViewModel() {
    var vehiclesLiveData = MutableLiveData<Resource<List<Vehicle>>>()


    init {
        getVehicles()
    }

    private fun getVehicles() {
        getCurrentVehiclesUseCase()
            .map { it.map { vehicle -> mapper.to(vehicle) } }
            .map { Resource.success(it) }
            .doOnSuccess {
                it?.let {
                    vehiclesLiveData.postValue(it)
                }
            }.doOnError {
                vehiclesLiveData.postValue(Resource.error("error"))
            }.subscribe()
    }

}