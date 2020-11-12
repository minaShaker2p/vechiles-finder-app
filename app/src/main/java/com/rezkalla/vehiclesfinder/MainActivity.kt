package com.rezkalla.vehiclesfinder

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rezkalla.vehiclesfinder.model.Resource
import com.rezkalla.vehiclesfinder.model.Status
import com.rezkalla.vehiclesfinder.ui.VehiclesViewModel
import com.rezkalla.vehiclesfinder.utils.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }


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
                    Log.d("viewModel", "success")

                }
                Status.ERROR -> {
                    Log.d("viewModel", "error")
                }
                Status.LOADING -> {
                    Log.d("viewModel", "loading")
                }
            }
        })
    }

}