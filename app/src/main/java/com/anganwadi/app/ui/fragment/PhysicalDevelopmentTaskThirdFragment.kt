package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskFirst1Binding
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskThirdBinding

class PhysicalDevelopmentTaskThirdFragment: BaseFragment() {
    private var _binding: FragmentPhyisicalDevelopmentTaskThirdBinding? = null
    private val binding get() = _binding!!
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhyisicalDevelopmentTaskThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        page1()

        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
        }
    }

    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach {
            it.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_white)
        }
    }

    private fun page1() {
        val arrayListImage =
            arrayOf(binding.iv1, binding.iv2, binding.iv3, binding.iv4)
        val arrayListTitle =
            arrayOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)
        arrayListTitle.forEachIndexed { index, textView ->
            textView.text = listOptionsTitle1[index].toString()
        }
        arrayListImage.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions1[index])

        }
    }
    private fun page2() {
        val arrayListImage =
            arrayOf(binding.iv1, binding.iv2, binding.iv3, binding.iv4)
        val arrayListTitle =
            arrayOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)
        arrayListTitle.forEachIndexed { index, textView ->
            textView.text = listOptionsTitle2[index]
        }
        arrayListImage.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions2[index])

        }
    }

    private fun page3() {
        val arrayListImage =
            arrayOf(binding.iv1, binding.iv2, binding.iv3, binding.iv4)
        val arrayListTitle =
            arrayOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)
        arrayListTitle.forEachIndexed { index, textView ->
            textView.text = listOptionsTitle3[index]
        }
        arrayListImage.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions3[index])

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}