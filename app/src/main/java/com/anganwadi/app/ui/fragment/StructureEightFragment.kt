package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentStructureEightBinding
import com.anganwadi.app.model.Question
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class StructureEightFragment: BaseFragment() {
    private var _binding: FragmentStructureEightBinding? = null
    private val binding get() = _binding!!
    lateinit var question: Question
    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureEightFragment {
            return StructureEightFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(TAG, question)
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStructureEightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(StructureSixFragment.TAG) ?: Question()
        binding.iv.setImageResource(R.drawable.ic_circle_image)
        binding.tvTitle.text=question.question?.questionText
        setImage(binding.iv, question.question?.questionImage?.after)
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        if(isDemo){
            binding.tvDemo.visibility=View.VISIBLE
        }else{
            binding.tvDemo.visibility=View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setImage(iv: ImageView, image: String?) {
        Glide.with(requireActivity())
            .load(image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv)
    }
}