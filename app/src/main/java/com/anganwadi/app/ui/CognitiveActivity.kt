package com.anganwadi.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anganwadi.app.databinding.ActivityCognitiveBinding
import com.anganwadi.app.ui.fragment.CognitiveTaskFifthFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskForthFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskSecondFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskThirdFragment
import com.anganwadi.app.ui.fragment.CognitivieTaskFirstFragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskFirst1Fragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskFirst2Fragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskFourthFragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskSecondFragment
import com.anganwadi.app.ui.fragment.PhysicalDevelopmentTaskThirdFragment

class CognitiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCognitiveBinding
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCognitiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            position = 1
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
                5 -> {
                    replaceFragment(CognitiveTaskFifthFragment())
                }
                6 -> {
                    replaceFragment(PhysicalDevelopmentTaskFirst1Fragment())
                }
                7 -> {
                    replaceFragment(PhysicalDevelopmentTaskFirst2Fragment())
                }
                8 -> {
                    replaceFragment(PhysicalDevelopmentTaskSecondFragment())
                }
                9 -> {
                    replaceFragment(PhysicalDevelopmentTaskThirdFragment())
                }
                10 -> {
                    replaceFragment(PhysicalDevelopmentTaskFourthFragment())
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
