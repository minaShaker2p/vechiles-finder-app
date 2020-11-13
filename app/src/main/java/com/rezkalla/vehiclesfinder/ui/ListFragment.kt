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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezkalla.vehiclesfinder.R
import com.rezkalla.vehiclesfinder.model.Status
import com.rezkalla.vehiclesfinder.utils.VerticalSpaceItemDecoration
import com.rezkalla.vehiclesfinder.utils.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : Fragment() {

    private val vehicleAdapter = VehicleAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<VehiclesViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(VehiclesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.vehiclesLiveData.observe(requireActivity(), Observer { results ->
            when (results.status) {
                Status.SUCCESS -> {
                    results.data?.let {
                        vehicleAdapter.update(it)
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(requireActivity(), results.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    Log.d("vehicleFinder", "loading")
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rclVehicles.apply {
            layoutManager =  LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(VerticalSpaceItemDecoration(24))
            adapter = vehicleAdapter
        }
    }

}