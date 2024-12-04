package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R

class CognitiveTaskThirdFragment : MultipleOptionsBaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frImageClue.visibility = View.GONE
        binding.iv.setImageResource(R.drawable.ic_cognitive_task_question)
        val drawable= arrayListOf(R.drawable.ic_cognitive_task_option1,
            R.drawable.ic_cognitive_task_option2,
            R.drawable.ic_cognitive_task_option3,
            R.drawable.ic_cognitive_task_option4)
        val arrayList =
            arrayOf(binding.ivBtnOne, binding.ivBtnTwo, binding.ivBtnThree, binding.ivBtnFour)
        arrayList.forEachIndexed { index, view ->
            view.setImageResource(drawable[index])
        }
    }
}