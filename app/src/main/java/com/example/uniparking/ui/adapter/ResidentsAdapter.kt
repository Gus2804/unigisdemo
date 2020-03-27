package com.example.uniparking.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uniparking.R
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.relations.VehicleWithStays
import kotlinx.android.synthetic.main.item_vehicle_resident.view.*

class ResidentsAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ResidentsAdapter.ViewHolder>() {

    var items : List<VehicleWithStays> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                val vehicle = items[adapterPosition].vehicle
                if(vehicle.accumulatedTime>0) {
                    onItemClickListener.onItemClick(
                        vehicle,
                        vehicle.accumulatedTime * 0.05
                    )
                }
            }
        }

        fun bind(vehicle : VehicleWithStays) {
            view.txtLicense.text = vehicle.vehicle.licensePlate
            view.txtPhone.text = view.context.getString(R.string.template_phone, vehicle.vehicle.phoneNumber)
            view.txtAccumulatedTime.text = view.context.getString(R.string.template_time, vehicle.vehicle.accumulatedTime)
            view.txtCurrentTotal.text = view.context.getString(R.string.template_total, "%.2f".format(vehicle.vehicle.accumulatedTime * 0.05))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle_resident, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface OnItemClickListener {
        fun onItemClick(vehicle: Vehicle, amount: Double)
    }

}