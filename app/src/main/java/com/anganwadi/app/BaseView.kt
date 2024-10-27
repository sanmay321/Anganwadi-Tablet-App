package com.anganwadi.app

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun View.showView() {
        this.visibility = View.VISIBLE
    }

    fun View.hideView() {
        this.visibility = View.GONE
    }

    fun logData(title: String, desc: String) {
        Log.d(title, desc)
    }
}