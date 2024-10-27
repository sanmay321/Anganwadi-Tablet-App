package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskFirstBinding

class CognitivieTaskFirstFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCognitiveTaskFirstBinding.inflate(inflater, container, false)
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
        setViewBaseOnAge(5.2)
    }

    private fun checkOrientation() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupPortraitLayout()
        }
    }

    private fun handleOrientation(orientation: Int) {
        _binding = FragmentCognitiveTaskFirstBinding.inflate(layoutInflater)
        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                setupPortraitLayout()
            }
        }
    }

    private fun setupPortraitLayout() {
        binding.apply {
            setTheMinusButtonToCenter()
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

    private fun setTheMinusButtonToCenter() {
        _binding?.btnPlus?.post {
            val plusWidth = binding.btnPlus.width

            val minusParams = _binding?.btnMinus?.layoutParams as LinearLayout.LayoutParams
            minusParams.width = plusWidth

            // Center horizontally
            val containerWidth = binding.bottomButtonsRow?.width ?: 0
            val horizontalMargin = (containerWidth - plusWidth) / 2
            minusParams.marginStart = horizontalMargin
            minusParams.marginEnd = horizontalMargin

            binding.btnMinus.layoutParams = minusParams
        }
    }

    private fun setViewBaseOnAge(ageRange: Double) {
        if (ageRange >= 3.0 && ageRange < 4) {
            binding.btnPlus.hideView()
            binding.btnCircle.hideView()
            binding.shapeSquare.hideView()
            binding.shapeTriangle.hideView()
            binding.iv.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_circle))
        } else if (ageRange in 4.0..5.0) {
            binding.iv.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_square))
            binding.btnMinus.hideView()
            binding.shapeTriangle.hideView()
            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                setupPortraitLayout()
            }
        }else{
            binding.iv.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_triangle))
        }
    }
}