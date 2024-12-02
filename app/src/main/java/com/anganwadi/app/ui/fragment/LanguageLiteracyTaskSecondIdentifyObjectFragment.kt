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

class LanguageLiteracyTaskSecondIdentifyObjectFragment : MultipleOptionsBaseFragment() {
    private val options =
        arrayListOf(R.drawable.ic_object_sun, R.drawable.ic_object_book, R.drawable.ic_object_car)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frQuestionImage.visibility = View.GONE
        binding.tvTitleQuestion.visibility = View.VISIBLE
        binding.llQuestion.visibility = View.GONE
        binding.frImageClue.visibility = View.GONE
        binding.tvTitle.text = "Identify the object.."
        binding.tvTitleQuestion.text = "Book"
        val imageOptions =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo,binding.ivBtnFour)
        imageOptions.forEachIndexed { index, imageView ->
            imageView.setImageResource(options[index])
        }
        binding.btnMinus.hideView()
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupPortraitLayout()
            setMarginVertical(4, includeBottom = false)
        }
    }
}