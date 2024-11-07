package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentPhyisicalDevelopmentTaskFourthBinding

class PhysicalDevelopmentTaskFourthFragment: BaseFragment() {
    private var _binding: FragmentPhyisicalDevelopmentTaskFourthBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhyisicalDevelopmentTaskFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.iv.setImageResource(R.drawable.ic_circle_image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}