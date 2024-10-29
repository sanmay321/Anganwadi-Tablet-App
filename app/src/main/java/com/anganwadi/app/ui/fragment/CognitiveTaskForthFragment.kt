package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskForthBinding

class CognitiveTaskForthFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskForthBinding? = null
    private val binding get() = _binding!!
    private val listQuestion = arrayListOf<Int>(
        R.drawable.ic_cookies,
        R.drawable.ic_pencil,
        R.drawable.ic_ball,
        R.drawable.ic_ball_pencil,
        R.drawable.ic_stack_cookies,
    )
    private val listOptions1 = arrayListOf<Int>(
        R.drawable.ic_cookies_6,
        R.drawable.ic_cookies_6,
        R.drawable.ic_cookies_6,
        R.drawable.ic_cookies_6
    )
    private val listOptions1Text = arrayListOf<String>(
        "Give all to friend",
        "Keep all for myself",
        "Share 3, keep 4",
        "Share 2, keep 4",
    )
    private val listOptions2 = arrayListOf<Int>(
        R.drawable.ic_pensil_1,
        R.drawable.ic_pensil_2,
        R.drawable.ic_pensil_3,
        R.drawable.ic_pensil_4
    )
    private val listOptions2Text = arrayListOf<String>(
        "1 Piece",
        "2 Pieces",
        "3 Pieces",
        "4 Pieces",
    )
    private val listOptions3 = arrayListOf<Int>(
        R.drawable.ic_coins_4,
        R.drawable.ic_coins_5,
        R.drawable.ic_coins_3,
        R.drawable.ic_coins_6
    )
    private val listOptions3Text = arrayListOf<String>(
        "4 Coins",
        "5 Coins",
        "3 Coins",
        "6 Coins"
    )
    private val listOptions4 = arrayListOf<Int>(
        R.drawable.ic_coins_16,
        R.drawable.ic_coins_19,
        R.drawable.ic_coins_20,
        R.drawable.ic_coins_21
    )
    private val listOptions4Text = arrayListOf<String>(
        "18 Coins",
        "19 Coins",
        "20 Coins",
        "21 Coins"
    )

    private val listOptions5 = arrayListOf<Int>(
        R.drawable.ic_cookies_5,
        R.drawable.ic_cookies_4,
        R.drawable.ic_cookies_6,
        R.drawable.ic_cookies_3,
    )
    private val listOptions5Text = arrayListOf<String>(
        "5 Biscuits",
        "4 Biscuits",
        "6 Biscuits",
        "3 Biscuits",
    )
    private val listQuestions = arrayListOf<String>(
        "How will you share 6 biscuits equally with your friend?",
        "If i break a pencil in half, how many pieces will i have?",
        "Prem has 4 coins, His mummy gives him 2 more. How many coin does he have now?",
        "One pencil costs 7 coins. How much do 3 pencils costs",
        "Count the biscuits in the image.")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCognitiveTaskForthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(5)
    }

    private fun setView(questionNumber: Int) {
        binding.tvTitle.text= listQuestions[questionNumber-1]
        binding.ivQuestion.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                listQuestion[questionNumber-1]
            )
        )
        val listImageView = arrayListOf(binding.iv1, binding.iv2, binding.iv3, binding.iv4)
        val listRl = arrayListOf(binding.rl1, binding.rl2, binding.rl3, binding.rl4)
        val listTextView = arrayListOf(binding.tvTitle1, binding.tvTitle2, binding.tvTitle3, binding.tvTitle4)
        listImageView.forEachIndexed { index, imageView ->
            when (questionNumber) {
                1 -> {
                    imageView.setImageResource(listOptions1[index])
                }
                2 -> {
                    imageView.setImageResource(listOptions2[index])
                }
                3 -> {
                    imageView.setImageResource(listOptions3[index])
                }
                4 -> {
                    imageView.setImageResource(listOptions4[index])
                }
                5 -> {
                    imageView.setImageResource(listOptions5[index])
                }
            }
        }
        listRl.forEachIndexed { index, relativeLayout ->
            relativeLayout.setOnClickListener {
                listRl.forEachIndexed { index2, relativeLayout2 ->
                    if(index2!=index){
                        relativeLayout2.background= ContextCompat.getDrawable(requireContext(), R.drawable.background_8_white)
                    }else{
                        relativeLayout2.background= ContextCompat.getDrawable(requireContext(), R.drawable.background_8_orange)

                    }
                }
            }
        }
        listTextView.forEachIndexed { index, textView ->
            when (questionNumber) {
                1 -> {
                    textView.text=listOptions1Text[index]
                }
                2 -> {
                    textView.text=listOptions2Text[index]
                }
                3 -> {
                    textView.text=listOptions3Text[index]
                }
                4 -> {
                    textView.text=listOptions4Text[index]
                }
                5 -> {
                    textView.text=listOptions5Text[index]
                }
            }
        }
    }
}