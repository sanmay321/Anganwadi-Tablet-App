package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.View
import com.anganwadi.app.model.Question

class StructureOneFragment : MultipleOptionsBaseFragment() {
    private var tag = ""

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureOneFragment {
            return StructureOneFragment().apply {
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
        binding.tvTitle.text = question.question?.questionText
        setImageQuestion(question.question?.questionImage?.after)
        setImageClue(question.question?.questionImage?.before)
        setUpForStructure1()
        val totalOptions=question.question?.totalOptions
        setUpOptionsView(totalOptions)
        setUpOptionsImage()
    }
}