package com.rezkalla.vehiclesfinder.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rezkalla.vehiclesfinder.R
import com.rezkalla.vehiclesfinder.model.Vehicle
import kotlinx.android.synthetic.main.item_vehicle.view.*

class VehicleAdapter : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    private val vehicles = mutableListOf<Vehicle>()

    fun update(list: List<Vehicle>) {
        vehicles.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun getItemCount() = vehicles.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicle = vehicles[position])
    }


    class VehicleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(vehicle: Vehicle) {
            view.tvVehicleId.text = vehicle.vehicleId
            view.tvBattery.text = "${vehicle.battery}%"
            view.tvState.text = vehicle.state
            view.tvZone.text = vehicle.zoneId
        }
    }
}