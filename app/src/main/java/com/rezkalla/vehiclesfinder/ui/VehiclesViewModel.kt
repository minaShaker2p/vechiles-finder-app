package com.rezkalla.vehiclesfinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.rezkalla.domain.usecase.GetCurrentVehiclesUseCase
import com.rezkalla.vehiclesfinder.mapper.VehicleMapper
import com.rezkalla.vehiclesfinder.model.Resource
import com.rezkalla.vehiclesfinder.model.Status
import com.rezkalla.vehiclesfinder.model.Vehicle
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.lang.Error
import javax.inject.Inject

class VehiclesViewModel @Inject constructor(
    private val getCurrentVehiclesUseCase: GetCurrentVehiclesUseCase,
    private val mapper: VehicleMapper
) : ViewModel() {
    init {
        getVehicles()
    }

    val vehiclesLiveData = MutableLiveData<Resource<List<Vehicle>>>()

    private fun getVehicles() {
        getCurrentVehiclesUseCase
            .buildUseCase()
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