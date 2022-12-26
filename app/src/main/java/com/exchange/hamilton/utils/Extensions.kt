package com.exchange.hamilton.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

fun Context.toast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}

fun Context.showKeyBoard(searchView: TextInputEditText) {
    searchView.apply {
        text = null
        requestFocus()
        val imm =
            this@showKeyBoard.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

}

fun Activity.hideSoftKeyboard() {
    val view = this.currentFocus
    view?.let {
        val imm =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

fun Long.toDateTime():DateTime{
    return DateTime(this)
}

fun DateTime.toDateTimeString():String{
    val fmt: DateTimeFormatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")
    return  fmt.print(DateTime(DateTimeZone.UTC))
}