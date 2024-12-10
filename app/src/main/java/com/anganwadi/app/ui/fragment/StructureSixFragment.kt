package com.anganwadi.app.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentStructureSixBinding
import com.anganwadi.app.model.Question
import com.anganwadi.app.ui.CognitiveActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StructureSixFragment : Fragment() {
    private var _binding: FragmentStructureSixBinding? = null
    private val binding get() = _binding!!
    private lateinit var question: Question
    private var correctAnswer: String=""

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureSixFragment {
            return StructureSixFragment().apply {
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
        _binding = FragmentStructureSixBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(TAG) ?: Question()
        question.question?.let {
            if (it.correctAnswer.isNotEmpty()) {
                correctAnswer=it.correctAnswer[0]
                Log.d("question ", "--> ${it.correctAnswer[0]}")
            }
        }
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        if(isDemo){
            binding.tvDemo.visibility=View.VISIBLE
        }else{
            binding.tvDemo.visibility=View.GONE
        }
        binding.tvTitle.text = question.question?.questionText
        Glide.with(requireActivity())
            .load(question.question?.questionImage?.after)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000)
                        setView()
                    }
                    return false
                }
            })
            .into(binding.iv)
    }

    private fun setView() {
        Glide.with(requireContext())
            .load(question.question?.answerImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.iv)
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        val arrayListTag =
            arrayOf("o1","o2","o3","o4")
        arrayList.forEachIndexed { index, view ->
            view.tag=arrayListTag[index]
            Glide.with(requireContext())
                .load(question.question?.option?.inactive)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
//            view.setImageResource(R.drawable.ic_egg_place_holder)
        }
        arrayList.forEach { view ->
            view.setOnClickListener {
                if(view.tag==correctAnswer){
                    Toast.makeText(requireContext(), "correct",Toast.LENGTH_SHORT).show()
                }
                clearAllBackground()
                Glide.with(requireContext())
                    .load(question.question?.option?.active)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
//                view.setImageResource(R.drawable.ic_egg_selected)
                (requireActivity() as CognitiveActivity).setUserAnswerTheQuestion()
            }
        }
    }

    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach { view ->
            view.setImageResource(R.drawable.ic_egg_place_holder)
        }
    }

}