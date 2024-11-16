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
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondAudioFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondIdentifyObjectFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondOddFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskSecondVisualFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskThirdLetterFragment
import com.anganwadi.app.ui.fragment.LanguageLiteracyTaskThirdLetterImageFragment
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
        val fragments= arrayListOf(
            CognitivieTaskFirstFragment(),
            CognitiveTaskSecondFragment(),
            CognitiveTaskThirdFragment(),
            CognitiveTaskForthFragment(),
            CognitiveTaskFifthFragment(),
            PhysicalDevelopmentTaskFirst1Fragment(),
            PhysicalDevelopmentTaskFirst2Fragment(),
            PhysicalDevelopmentTaskSecondFragment(),
            PhysicalDevelopmentTaskThirdFragment(),
            PhysicalDevelopmentTaskFourthFragment(),
            LanguageLiteracyTaskSecondAudioFragment(),
            LanguageLiteracyTaskSecondVisualFragment(),
            LanguageLiteracyTaskSecondOddFragment(),
            LanguageLiteracyTaskSecondIdentifyObjectFragment(),
            LanguageLiteracyTaskThirdLetterImageFragment(),
            LanguageLiteracyTaskThirdLetterFragment(),
        )
        if (savedInstanceState == null) {
            position = 1
            replaceFragment(fragments[0])
        }
        binding.btnNext.setOnClickListener {
            if(position<fragments.size) {
                position++
                replaceFragment(fragments[position - 1])
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
