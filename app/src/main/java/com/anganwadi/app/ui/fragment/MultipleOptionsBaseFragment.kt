package com.anganwadi.app.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskFirstBinding

open class MultipleOptionsBaseFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskFirstBinding? = null
    val binding get() = _binding!!

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
        val arrayList =
            arrayOf(binding.btnOne, binding.btnTwo, binding.btnMinus, binding.btnFour)
        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
        }
    }

    fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.btnOne, binding.btnTwo, binding.btnMinus, binding.btnFour)
        arrayList.forEach {
            it.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_white)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setTheMinusButtonToCenter() {
        _binding?.btnOne?.post {
            val plusWidth = binding.btnOne.width

            val minusParams = _binding?.btnMinus?.layoutParams as LinearLayout.LayoutParams
            minusParams.width = plusWidth

            // Center horizontally
            val containerWidth = binding.bottomButtonsRow?.width ?: 0
            val horizontalMargin = (containerWidth - plusWidth) / 2
            minusParams.marginStart = horizontalMargin
            minusParams.marginEnd = horizontalMargin

            binding.btnFour.layoutParams = minusParams
        }
    }

    fun setupPortraitLayout() {
        binding.apply {
            setTheMinusButtonToCenter()
        }
    }

    fun setMarginVertical(value: Int, includeBottom: Boolean = true) {
        binding.cl?.post {
            val screenWidth = Resources.getSystem().displayMetrics.widthPixels
            val layoutParams = binding.cl?.layoutParams as? ViewGroup.MarginLayoutParams

            // Set height to screen width
            layoutParams?.height = screenWidth

            // Calculate vertical margin as 1/4 of the screen width
            val verticalMargin = screenWidth / value

            // Set top and bottom margins
            layoutParams?.topMargin = verticalMargin
            if (includeBottom) {
                layoutParams?.bottomMargin = verticalMargin
            }

            // Apply updated layout parameters
            binding.cl?.layoutParams = layoutParams
        }
    }

}