package com.anganwadi.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    fun goToPage(cls: Class<*>?) {
        val intent = Intent(activity, cls)
        startActivity(intent)
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
    fun goToPageAndClearPrevious(cls: Class<*>?) {
        val intent = Intent(activity, cls)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun goToPageAndClearPrevious(cls: Class<*>?, bundle: Bundle?) {
        val intent = Intent(activity, cls)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }

    fun goToPage(cls: Class<*>?, bundle: Bundle?) {
        val intent = Intent(activity, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }

    fun showToast(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}