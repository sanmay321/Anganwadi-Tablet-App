package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentLanguageLiteracyTaskSecondIdentifyObjectBinding

class LanguageLiteracyTaskSecondIdentifyObjectFragment : Fragment() {
    private var _binding: FragmentLanguageLiteracyTaskSecondIdentifyObjectBinding? = null
    private val binding get() = _binding!!
    private val options =
        arrayListOf(R.drawable.ic_object_sun, R.drawable.ic_object_book, R.drawable.ic_object_car)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageLiteracyTaskSecondIdentifyObjectBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3)
        val imageOptions =
            arrayOf(binding.iv1, binding.iv2, binding.iv3)

        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
        }
        imageOptions.forEachIndexed { index, imageView ->
            imageView.setImageResource(options[index])
        }
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setTheMinusButtonToCenter()
        }
    }

    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach {
            it?.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_white)
        }
    }

    private fun setTheMinusButtonToCenter() {
        _binding?.rl4?.post {
            val plusWidth = binding.rl4!!.width

            val minusParams = _binding?.rl3?.layoutParams as LinearLayout.LayoutParams
            minusParams.width = plusWidth

            // Center horizontally
            val containerWidth = binding.bottomButtonsRow?.width ?: 0
            val horizontalMargin = (containerWidth - plusWidth) / 2
            minusParams.marginStart = horizontalMargin
            minusParams.marginEnd = horizontalMargin
            binding.rl3.layoutParams = minusParams
            _binding?.rl4?.visibility = View.GONE
        }
    }
}