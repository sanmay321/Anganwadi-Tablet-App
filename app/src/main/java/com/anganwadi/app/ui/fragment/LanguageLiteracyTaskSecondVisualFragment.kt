package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentLanguageLiteracyTaskSecondVisualBinding

class LanguageLiteracyTaskSecondVisualFragment : MultipleOptionsBaseFragment() {
    private val listOptions= arrayListOf("L", "M", "M","M")
    private val listImages= arrayListOf(
        R.drawable.ic_ll_anaar,
        R.drawable.ic_ll_banana,
        R.drawable.ic_ll_boat,
        R.drawable.ic_ll_cap,
        R.drawable.ic_ll_car,
        R.drawable.ic_ll_cat,
        R.drawable.ic_ll_cup,
        R.drawable.ic_ll_dog,
        R.drawable.ic_ll_duck,
        R.drawable.ic_ll_fan,
        R.drawable.ic_ll_flower,
        R.drawable.ic_ll_ginger,
        R.drawable.ic_ll_jackfruit,
        R.drawable.ic_ll_jahaaj,
        R.drawable.ic_ll_lion,
        R.drawable.ic_ll_pear,
        R.drawable.ic_ll_plant,
        R.drawable.ic_ll_rabbit,
        R.drawable.ic_ll_shoe,
        R.drawable.ic_ll_snake,
        R.drawable.ic_ll_spoon,
        R.drawable.ic_ll_sun,
        R.drawable.ic_ll_tamatar,
        R.drawable.ic_ll_tree,
        R.drawable.ic_ll_tree_2,
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.GONE
        binding.llQuestion.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        binding.ivIconSound.visibility = View.VISIBLE
        binding.tvTitle.text="Match the letter with image"

    }

}