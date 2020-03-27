package com.example.uniparking.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uniparking.R
import com.example.uniparking.data.db.entity.Stay
import com.example.uniparking.ui.adapter.ParkedVehiclesAdapter
import com.example.uniparking.ui.viewmodel.ParkedVehicleViewModel
import com.example.uniparking.utils.hideKeyboard
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_parked.*
import kotlinx.android.synthetic.main.dialog_new_check_in.view.*
import kotlinx.android.synthetic.main.dialog_new_check_in.view.edtLicensePlate
import kotlinx.android.synthetic.main.dialog_new_resident.view.*
import javax.inject.Inject

class ParkedVehiclesActivity: DaggerAppCompatActivity(), ParkedVehiclesAdapter.OnItemEventsListener{

    @Inject
    lateinit var viewModel : ParkedVehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_parked)

        rvVehicles.adapter = ParkedVehiclesAdapter(this)
        rvVehicles.layoutManager = LinearLayoutManager(this)

        btnCheckIn.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_check_in,null)
            val dialog : AlertDialog.Builder = AlertDialog.Builder(this)

            dialog
                .setView(view)
                .setTitle(R.string.title_new_check_in_time)
                .setPositiveButton(R.string.btn_save) { dialogI, _ ->
                    viewModel.saveStay(view.edtLicensePlate.text.toString())
                    hideKeyboard()
                    dialogI.dismiss()

                }
                .setNegativeButton(R.string.btn_cancel) { dialogI, _ ->
                    hideKeyboard()
                    dialogI.cancel()
                }.show()
        }

        viewModel.stays.observe(this, Observer {
            (rvVehicles.adapter as ParkedVehiclesAdapter).items = it
        })

        viewModel.amount.observe(this, Observer {
            val dialog : AlertDialog.Builder = AlertDialog.Builder(this)
            dialog
                .setMessage(it)
                .setTitle(R.string.title_out)
                .setPositiveButton(R.string.btn_save) { dialogI, _ ->
                    dialogI.dismiss()
                }.show()
        })

        viewModel.prepareObservers(this)
    }

    override fun onRegisterCheckOutTime(stay: Stay) {
        viewModel.setCheckoutTime(stay)
    }

    override fun onSaveAsOfficial(license: String) {
        viewModel.saveVehicleAsOfficial(license)
    }

    override fun onSaveAsResident(license: String) {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_resident,null)
        view.edtLicensePlate.visibility = View.GONE
        val dialog : AlertDialog.Builder = AlertDialog.Builder(this)

        dialog
            .setView(view)
            .setTitle(R.string.title_save_resident)
            .setPositiveButton(R.string.btn_save) { dialogI, _ ->
                viewModel.saveVehicleAsResident(license, view.edtPhone.text.toString())
                hideKeyboard()
                dialogI.dismiss()

            }
            .setNegativeButton(R.string.btn_cancel) { dialogI, _ ->
                hideKeyboard()
                dialogI.cancel()
            }.show()
    }

}