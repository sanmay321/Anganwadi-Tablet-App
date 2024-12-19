package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.View
import com.anganwadi.app.model.Question

class StructureTwoFragment : MultipleOptionsBaseFragment() {
    private var tag = ""

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureTwoFragment {
            return StructureTwoFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(TAG, question)
                arguments = bundle
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(StructureSixFragment.TAG) ?: Question()
        question.question?.let {
            if (it.correctAnswer.isNotEmpty()) {
                correctAnswer = it.correctAnswer[0]
            }
        }
        binding.tvTitle.text = question.question?.questionText
        setImageQuestion(question.question?.questionImage?.after)
        setUpForStructure2()
        val totalOptions=question.question?.totalOptions
        setUpOptionsView(totalOptions)
        setUpOptionsImage()
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        setDemoView(isDemo)
    }
}