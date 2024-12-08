package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.ActivityAestheticTaskSecondBinding
import com.anganwadi.app.databinding.FragmentLanguageLiteracyTaskSecondVisualBinding

class AestheticTaskSecondFragment : Fragment() {
    private var _binding: ActivityAestheticTaskSecondBinding? = null
    private val binding get() = _binding!!
    private val listOptions= arrayListOf(
        R.drawable.ic_tiger_object,
        R.drawable.ic_tortoise_object,
        R.drawable.ic_deer_object,
        R.drawable.ic_zebra_object)
    private val listSiluet= arrayListOf(
        R.drawable.ic_siluet_tiger,
        R.drawable.ic_siluet_tortoise,
        R.drawable.ic_siluet_deer,
        R.drawable.ic_siluet_zebra)
    private val listSkin= arrayListOf(
        R.drawable.ic_skin_tiger,
        R.drawable.ic_skin_tortoise,
        R.drawable.ic_skin_deer,
        R.drawable.ic_skin_zebra)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityAestheticTaskSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayList =
            arrayOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        val arrayListImv =
            arrayOf(binding.iv1, binding.iv2, binding.iv3, binding.iv4)
        arrayListImv.forEachIndexed { index, imageView ->
            imageView.setImageResource(listOptions[index])
        }
        binding.ivIconSound.setImageResource(listSiluet[0])

        arrayList.forEach { view ->
            view.setOnClickListener {
                clearAllBackground()
                view.background =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.background_8_orange)
            }
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