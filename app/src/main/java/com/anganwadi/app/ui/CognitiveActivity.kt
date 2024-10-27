package com.anganwadi.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anganwadi.app.databinding.ActivityCognitiveBinding
import com.anganwadi.app.ui.fragment.CognitiveTaskSecondFragment
import com.anganwadi.app.ui.fragment.CognitiveTaskThirdFragment
import com.anganwadi.app.ui.fragment.CognitivieTaskFirstFragment

class CognitiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCognitiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCognitiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Add the fragment if not already added (after configuration change)
        if (savedInstanceState == null) {
            replaceFragment(CognitivieTaskFirstFragment())
        }
        binding.btnNext.setOnClickListener {
            replaceFragment(CognitiveTaskSecondFragment())
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null) // Add to back stack to handle back navigation
            .commit()
    }

    // Handle back navigation to the previous fragment
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack() // Go to the previous fragment
        } else {
            super.onBackPressed() // Exit the activity
        }
    }
}
