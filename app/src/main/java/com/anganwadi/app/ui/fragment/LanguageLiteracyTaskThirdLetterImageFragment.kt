package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anganwadi.app.QuestionAdapter
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentLanguageLiteracyTaskThirdLetterImageBinding

class LanguageLiteracyTaskThirdLetterImageFragment: Fragment() {
    private var _binding: FragmentLanguageLiteracyTaskThirdLetterImageBinding? = null
    private val binding get() = _binding!!
    private val listOptions= arrayListOf("L", "M", "M","M")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageLiteracyTaskThirdLetterImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        val arrayListOptionsId =
            arrayOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)

        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
        }
        binding.rv?.apply {
            layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter= QuestionAdapter(requireContext(), "BOOK".toList())
        }
        val options= listOptions
        arrayListOptionsId.forEachIndexed { index, textView ->
            textView.text=options[index]
        }

    }
    private fun clearAllBackground() {
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        arrayList.forEach {
            it.background =
                ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_white)
        }
    }
}