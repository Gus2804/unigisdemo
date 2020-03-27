package com.example.uniparking.ui.adapter

import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.uniparking.R
import com.example.uniparking.data.db.entity.Stay
import kotlinx.android.synthetic.main.item_vehicle_parked.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ParkedVehiclesAdapter(val eventListener: OnItemEventsListener) : RecyclerView.Adapter<ParkedVehiclesAdapter.ViewHolder>() {

    var items : List<Stay> = ArrayList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle_parked, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view), PopupMenu.OnMenuItemClickListener {

        init {
            view.btnRegisterCheckOut.setOnClickListener {
                eventListener.onRegisterCheckOutTime(items[adapterPosition])
            }
            view.btnOptions.setOnClickListener {
                showPopup(it)
            }
        }

        fun bind(stay: Stay) {
            view.txtLicense.text = stay.vehicleLicensePlate
            view.txtCheckInTime.text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(stay.checkInTime)
        }

        private fun showPopup(v: View) {
            val popup = PopupMenu(view.context, v)
            popup.setOnMenuItemClickListener(this@ViewHolder)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.menu_parked_items, popup.menu)
            popup.show()
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return when(item?.itemId) {
                R.id.menu_save_official -> {
                    eventListener.onSaveAsOfficial(items[adapterPosition].vehicleLicensePlate)
                    true
                }
                R.id.menu_save_resident -> {
                    eventListener.onSaveAsResident(items[adapterPosition].vehicleLicensePlate)
                    true
                }
                else -> false
            }
        }
    }

    interface OnItemEventsListener {
        fun onRegisterCheckOutTime(stay: Stay)
        fun onSaveAsOfficial(license : String)
        fun onSaveAsResident(license : String)
    }

}