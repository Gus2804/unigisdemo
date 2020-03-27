package com.example.uniparking.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun <T : Activity> Activity.goToActivity(activity: Class<T>, extras : Bundle? = null, flags : Int? = null, requestCode : Int? = null) {
    val intent = Intent(this, activity)
    with(intent) {
        extras?.let {
            putExtras(it)
        }
        flags?.let {
            this.flags = flags
        }
    }
    if(requestCode==null) {
        startActivity(intent)
    } else {
        startActivityForResult(intent,requestCode)
    }
}

fun <T : Activity> Fragment.goToActivity(activity: Class<T>, extras : Bundle? = null, flags : Int? = null, requestCode : Int? = null) {
    val intent = Intent(this.activity, activity)
    with(intent) {
        extras?.let {
            putExtras(it)
        }
        flags?.let {
            this.flags = flags
        }
    }
    if(requestCode==null) {
        startActivity(intent)
    } else {
        startActivityForResult(intent,requestCode)
    }
}

fun Date.format(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    return formatter.format(this)
}

fun String.toHour(): String? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    val hourFormatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return try {
        val date = formatter.parse(this)
        return hourFormatter.format(date)
    } catch (ex: Exception) {
        null
    }
}
