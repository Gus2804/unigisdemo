package com.example.uniparking.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uniparking.R
import com.example.uniparking.data.db.entity.Vehicle
import com.example.uniparking.ui.adapter.ResidentsAdapter
import com.example.uniparking.ui.viewmodel.ResidentsViewModel
import com.example.uniparking.utils.hideKeyboard
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_resident.*
import kotlinx.android.synthetic.main.dialog_new_resident.view.*
import javax.inject.Inject


class ResidentsActivity : DaggerAppCompatActivity(), ResidentsAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModel: ResidentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resident)

        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.adapter = ResidentsAdapter(this)

        btnAdd.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_resident,null)
            val dialog : AlertDialog.Builder = AlertDialog.Builder(this)

            dialog
                .setView(view)
                .setTitle(R.string.title_save_resident)
                .setPositiveButton(R.string.btn_save) { dialogI, _ ->
                    viewModel.saveResident(view.edtLicensePlate.text.toString(), view.edtPhone.text.toString())
                    hideKeyboard()
                    dialogI.dismiss()

                }
                .setNegativeButton(R.string.btn_cancel) { dialogI, _ ->
                    hideKeyboard()
                    dialogI.cancel()
                }.show() }

        observeViewModel()
    }

    private fun observeViewModel() {
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