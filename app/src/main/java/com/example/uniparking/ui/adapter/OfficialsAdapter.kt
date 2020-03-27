package com.example.uniparking.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uniparking.R
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.data.db.relations.VehicleWithStays
import kotlinx.android.synthetic.main.item_vehicle_official.view.*

class OfficialsAdapter() : RecyclerView.Adapter<OfficialsAdapter.ViewHolder>() {

    var items : List<VehicleWithStays> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(vehicle : VehicleWithStays) {

            view.txtLicense.text = vehicle.vehicle.licensePlate
            view.txtVisits.text = view.context.getString(R.string.template_visits, vehicle.stays.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle_official, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}