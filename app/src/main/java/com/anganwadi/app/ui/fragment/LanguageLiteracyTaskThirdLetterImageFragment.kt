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

class LanguageLiteracyTaskThirdLetterImageFragment : Fragment() {
    private var _binding: FragmentLanguageLiteracyTaskThirdLetterImageBinding? = null
    private val binding get() = _binding!!
    private val listOptions = arrayListOf("L", "M", "M", "M")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentLanguageLiteracyTaskThirdLetterImageBinding.inflate(inflater, container, false)
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
        binding.ivIcon.setImageResource(getImage(0))
        binding.rv.apply {
            val questionText = getQuestionText(0)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = QuestionAdapter(requireContext(), questionText)
        }
        val options = listOptions
        arrayListOptionsId.forEachIndexed { index, textView ->
            textView.text = options[index]
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

    private fun getQuestionText(index: Int): List<Char> {
        val listQuestion = arrayListOf<String>(
            "BAL?",
            "?AT",
            "BI?D",
            "G?ASS",
            "TIG?R",
            "B?OK",
        )
        return listQuestion[index].toList()
    }

    private fun getImage(index: Int): Int {
        val listImage = arrayListOf<Int>(
            R.drawable.ic_object_ball,
            R.drawable.ic_object_cat,
            R.drawable.ic_object_bird,
            R.drawable.ic_object_grass,
            R.drawable.ic_object_tiger,
            R.drawable.ic_object_book,
            R.drawable.ic_object_glass,
            R.drawable.ic_object_fan
        )
        return listImage[index]
    }

}