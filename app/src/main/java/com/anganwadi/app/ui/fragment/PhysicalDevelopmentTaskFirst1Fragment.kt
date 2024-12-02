package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import com.anganwadi.app.R
import com.anganwadi.app.util.SessionManager

class PhysicalDevelopmentTaskFirst1Fragment : MultipleOptionsBaseFragment() {
    private val listOptions1 = arrayListOf(
        R.drawable.ic_bread,
        R.drawable.ic_burger,
        R.drawable.ic_milk,
        R.drawable.ic_fish
    )
    private val listOptionsTitle1 = arrayListOf(
        "Bread",
        "Burger",
        "Milk",
        "Fish"
    )
    private val listOptions2 = arrayListOf(
        R.drawable.ic_milk,
        R.drawable.ic_cold_drink,
        R.drawable.ic_water,
        R.drawable.ic_juice
    )
    private val listOptionsTitle2 = arrayListOf(
        "Milk",
        "Cold drink",
        "Water",
        "Orange Juice"
    )
    private val listOptions3 = arrayListOf(
        R.drawable.ic_bread,
        R.drawable.ic_chocolates,
        R.drawable.ic_fruit_basket,
        R.drawable.ic_flat_bread
    )
    private val listOptionsTitle3 = arrayListOf(
        "Bread",
        "Chocolates",
        "Fruits",
        "Bread"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ageRange = SessionManager.getAge(requireContext())
        binding.frQuestionImage.visibility = View.GONE
        binding.llQuestion.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        binding.tvTitle.text = "Select unhealthy food."
        val orientation = resources.configuration.orientation
        when (ageRange) {
            "3-4" -> {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    binding.bottomButtonsRow?.hideView()
                    setMarginVertical(4)
                } else {
                    setMarginVertical(10)
                }
                binding.btnMinus.hideView()
                binding.btnFour.hideView()
                page1()
            }

            "4-5" -> {
                binding.btnMinus.hideView()
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setupPortraitLayout()
                    setMarginVertical(8)
                } else {
                    setMarginVertical(10)
                }
                page2()
            }

            else -> {
                page3()
                setMarginVertical(10)
            }
        }
    }

    private fun page1() {
        val arrayList =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnMinus, binding.ivBtnFour)
        arrayList.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions1[index])
        }
    }

    private fun page2() {
        val arrayList =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnMinus, binding.ivBtnFour)
        arrayList.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions2[index])
        }
        binding.btnMinus.hideView()
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupPortraitLayout()
        }
    }

    private fun page3() {
        val arrayList =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnMinus, binding.ivBtnFour)
        arrayList.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions3[index])
        }
    }
}