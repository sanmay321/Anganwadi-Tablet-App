package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.View
import com.anganwadi.app.model.Question

class StructureThreeFragment : MultipleOptionsBaseFragment() {
    private var tag = ""

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureThreeFragment {
            return StructureThreeFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(TAG, question)
                arguments = bundle
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(AestheticTaskFirstFragment.TAG) ?: Question()
        question.question?.let {
            if (it.correctAnswer.isNotEmpty()) {
                correctAnswer = it.correctAnswer[0]
            }
        }
        binding.tvTitle.text =""
        binding.tvTitleQuestion.text = question.question?.questionText
        setUpForStructure3()
        val totalOptions=question.question?.totalOptions
        setUpOptionsView(totalOptions)
        setUpOptionsImage()
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        if(isDemo){
            binding.tvDemo.visibility=View.VISIBLE
        }else{
            binding.tvDemo.visibility=View.GONE
        }
    }
}