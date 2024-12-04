package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.anganwadi.app.R
import com.anganwadi.app.util.SessionManager

class CognitivieTaskFirstFragment : MultipleOptionsBaseFragment() {
    private var tag=""
    companion object{
        const val TAG="tag"
        fun newFragment(tag: String) : CognitivieTaskFirstFragment{
            return CognitivieTaskFirstFragment().apply {
                val bundle = Bundle()
                bundle.putString(TAG, tag)
                arguments = bundle
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text="Select the correct symbol inside the shape"
        setViewBaseOnAge(SessionManager.getAge(requireContext()))
    }


    private fun setViewBaseOnAge(ageRange: String) {
        if (ageRange == "3-4") {
            binding.btnThree.hideView()
            binding.btnFour.hideView()
            binding.shapeSquare.hideView()
            binding.shapeTriangle.hideView()
            binding.iv.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_circle
                )
            )
        } else if (ageRange == "4-5") {
            binding.iv.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_square
                )
            )
            binding.btnThree.hideView()
            binding.shapeTriangle.hideView()
            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                setupPortraitLayout()
            }
        } else {
            binding.iv.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_triangle
                )
            )
        }
    }
}