package com.rezkalla.vehiclesfinder.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rezkalla.vehiclesfinder.R
import com.rezkalla.vehiclesfinder.model.Status
import com.rezkalla.vehiclesfinder.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MapFragment : Fragment(), OnMapReadyCallback {

    private var map: GoogleMap? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VehiclesViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(VehiclesViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.vehiclesLiveData.observe(requireActivity(), Observer { results ->
            when (results.status) {
                Status.SUCCESS -> {
                    addVehicles(results.data?.map {
                        LatLng(it.latitude, it.longitude)
                    })
                }
                Status.ERROR -> {
                    Toast.makeText(requireActivity(), results.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    Log.d("vehicleFinder", "loading")
                }
            }
        })

        val mapFragment: SupportMapFragment? = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
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


}