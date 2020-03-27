package com.example.uniparking.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uniparking.R
import com.example.uniparking.ui.adapter.OfficialsAdapter
import com.example.uniparking.ui.adapter.ResidentsAdapter
import com.example.uniparking.ui.viewmodel.OfficialsViewModel
import com.example.uniparking.utils.hideKeyboard
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_officials.*
import kotlinx.android.synthetic.main.dialog_new_official.view.*
import javax.inject.Inject

class OfficialsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: OfficialsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_officials)

        rvVehicles.layoutManager = LinearLayoutManager(this)
        rvVehicles.adapter = OfficialsAdapter()

        btnAdd.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_official,null)
            val dialog : AlertDialog.Builder = AlertDialog.Builder(this)

            dialog
                .setView(view)
                .setTitle(R.string.title_save_resident)
                .setPositiveButton(R.string.btn_save) { dialogI, _ ->
                    viewModel.saveOfficial(view.edtLicensePlate.text.toString())
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
            (rvVehicles.adapter as OfficialsAdapter).items = it
        })
    }

}