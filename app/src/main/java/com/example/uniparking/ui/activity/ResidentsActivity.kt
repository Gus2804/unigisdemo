package com.example.uniparking.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uniparking.R
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.ui.adapter.ResidentsAdapter
import com.example.uniparking.ui.viewmodel.ResidentsViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_resident.*
import javax.inject.Inject


class ResidentsActivity : DaggerAppCompatActivity(), ResidentsAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModel: ResidentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resident)

        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.adapter = ResidentsAdapter(this)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.vehicles.observe(this, Observer {
            (rvVehicles.adapter as ResidentsAdapter).items = it
        })
    }

    override fun onItemClick(vehicle: Vehicle, amount: Double) {
        try {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                setPackage("com.whatsapp")
                val time = if (vehicle.accumulatedTime > 60)
                    "${resources.getQuantityString(
                        R.plurals.template_hous,
                        vehicle.accumulatedTime / 60,
                        vehicle.accumulatedTime / 60
                    )} " +
                            resources.getQuantityString(
                                R.plurals.template_minutes,
                                vehicle.accumulatedTime % 60,
                                vehicle.accumulatedTime % 60
                            )
                else
                    resources.getQuantityString(
                        R.plurals.template_minutes,
                        vehicle.accumulatedTime,
                        vehicle.accumulatedTime
                    )
                putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.template_message, vehicle.licensePlate, time, amount)
                )
                putExtra("jid", "521" + vehicle.phoneNumber + "@s.whatsapp.net");
            }
            startActivity(sendIntent)
        }catch (ex : Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "No se puede enviar el mensaje", Toast.LENGTH_SHORT).show()
        }
    }

}