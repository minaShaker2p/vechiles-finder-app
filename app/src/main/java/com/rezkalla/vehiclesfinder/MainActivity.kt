package com.rezkalla.vehiclesfinder

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rezkalla.vehiclesfinder.model.Status
import com.rezkalla.vehiclesfinder.ui.VehiclesViewModel
import com.rezkalla.vehiclesfinder.utils.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasAndroidInjector, OnMapReadyCallback {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    private var map: GoogleMap? = null


    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VehiclesViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(VehiclesViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.vehiclesLiveData.observe(this, Observer { results ->
            when (results.status) {
                Status.SUCCESS -> {
                    addVehicles(results.data?.map {
                        LatLng(it.latitude, it.longitude)
                    })
                }
                Status.ERROR -> {
                    Toast.makeText(this,results.message,Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    Log.d("vehicleFinder", "loading")
                }
            }
        })

        val mapFragment: SupportMapFragment? = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun addVehicles(vehicleLatLng: List<LatLng>?) {
        map?.let { googleMap ->
            vehicleLatLng?.forEach { latlng ->
                googleMap.apply {
                    addMarker(
                        MarkerOptions()
                            .position(latlng)
                    )
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 6f))
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
    }

}