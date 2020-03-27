package com.example.uniparking.utils

import android.text.Editable
import android.text.TextWatcher

class LicenseTextWatcher : TextWatcher {

    private val prevLength = 0

    override fun afterTextChanged(s: Editable?) {
        //Borrando
        if(prevLength > s?.length?:0) {
            if(s?.length==5) {

            }
        }
        //Escribiendo
        else {
            if(s?.length == 4) {
                s.insert(3, " - ")
            }
        }

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}