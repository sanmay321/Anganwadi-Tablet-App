package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskFirst1Binding

class LanguageLiteracyTaskSecondOddFragment: MultipleOptionsBaseFragment() {
    private val listOptions1 = arrayListOf(
        R.drawable.ic_odd_cake,
        R.drawable.ic_odd_earth,
        R.drawable.ic_odd_apple,
        R.drawable.ic_odd_banana
    )
    private val listOptionsTitle1 = arrayListOf(
        "Cake",
        "Earth",
        "Apple",
        "Banana"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.GONE
        binding.llQuestion?.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        binding.tvTitle.text="Choose the odd one out"
        page1()
        setMarginVertical(4)
    }


    private fun page1() {
        val arrayListImage =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnMinus, binding.ivBtnFour)
        arrayListImage.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions1[index])

        }
    }
}