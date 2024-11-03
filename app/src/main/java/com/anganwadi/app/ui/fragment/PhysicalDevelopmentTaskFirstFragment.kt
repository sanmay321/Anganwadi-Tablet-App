package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTastThirdBinding
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskFirst2Binding

class PhysicalDevelopmentTaskFirstFragment : BaseFragment() {
    private var _binding: FragmentPhyisicalDevelopmentTaskFirst2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhyisicalDevelopmentTaskFirst2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.llGood, binding.llBad)
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
            arrayOf(binding.llGood, binding.llBad)
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