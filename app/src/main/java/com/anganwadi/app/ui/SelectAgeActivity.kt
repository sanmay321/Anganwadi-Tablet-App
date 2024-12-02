package com.anganwadi.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anganwadi.app.databinding.ActivityAgeBinding
import com.anganwadi.app.util.SessionManager

class SelectAgeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgeBinding
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arrayList =
            arrayOf(binding.btn3, binding.btn4, binding.btn5)
        arrayList.forEach { it ->
            it.setOnClickListener {
                SessionManager.setAge(this, it.tag.toString())
            }
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, CognitiveActivity::class.java))
        }
    }
}
