package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTastThirdBinding

class CognitiveTaskThirdFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTastThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCognitiveTastThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEqual.visibility = View.VISIBLE
        val arrayList =
            arrayOf(binding.btnPlus, binding.btnCircle, binding.btnMinus, binding.btnEqual)
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
            arrayOf(binding.btnPlus, binding.btnCircle, binding.btnMinus, binding.btnEqual)
        arrayList.forEach {
            it.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_white)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}