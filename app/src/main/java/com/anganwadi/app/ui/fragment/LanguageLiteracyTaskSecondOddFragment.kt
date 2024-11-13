package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskFirst1Binding

class LanguageLiteracyTaskSecondOddFragment: BaseFragment() {
    private var _binding: FragmentPhyisicalDevelopmentTaskFirst1Binding? = null
    private val binding get() = _binding!!
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhyisicalDevelopmentTaskFirst1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text="Choose the odd one out"
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}