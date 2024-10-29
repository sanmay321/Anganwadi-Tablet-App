package com.anganwadi.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anganwadi.app.databinding.ActivityCognitiveBinding
import com.anganwadi.app.ui.fragment.CognitiveTaskForthFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskSecondFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskThirdFragment
import com.anganwadi.app.ui.fragment.CognitivieTaskFirstFragment

class CognitiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCognitiveBinding
    private var position=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCognitiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            position=1
            replaceFragment(CognitivieTaskFirstFragment())
        }
        binding.btnNext.setOnClickListener {
            position++
            when (position) {
                2 -> {
                    replaceFragment(CognitiveTaskSecondFragment())
                }
                3 -> {
                    replaceFragment(CognitiveTaskThirdFragment())
                }
                4 -> {
                    replaceFragment(CognitiveTaskForthFragment())
                }
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Handle back navigation to the previous fragment
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            position--
            supportFragmentManager.popBackStack() // Go to the previous fragment
        } else {
            super.onBackPressed() // Exit the activity
        }
    }
}
