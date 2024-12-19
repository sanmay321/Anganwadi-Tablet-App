package com.anganwadi.app.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentStructureSevenBinding
import com.anganwadi.app.model.Question
import com.anganwadi.app.ui.CognitiveActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Item(val valName: String, var src: Int = 0)
class StructureSevenFragment : BaseFragment() {
    private var _binding: FragmentStructureSevenBinding? = null
    private val binding get() = _binding!!
    private var selectedSense: View? = null
    private var listTrueConnection: ArrayList<String> = ArrayList()
    lateinit var question: Question

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureSevenFragment {
            return StructureSevenFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(TAG, question)
                arguments = bundle
            }
        }
    }

    val leftDemo = arrayListOf(
        Item("त्वचा"),
        Item("आंख"),
        Item("कान"),
        Item("नाक"),
        Item("जीभ")
    )

    val leftQues = arrayListOf(
        Item("जीभ"),
        Item("आंख"),
        Item("कान"),
        Item("त्वचा"),
        Item("नाक")
    )

    // RightColumn
    val rightDemo = arrayListOf(
        Item("Candle", R.drawable.ic_candle),
        Item("IceCream", R.drawable.ic_ice_cream),
        Item("Perfume", R.drawable.ic_perfume),
        Item("Teddy", R.drawable.ic_teddy),
        Item("Speaker", R.drawable.ic_speaker)
    )

    val rightQues = arrayListOf(
        Item("Ball", R.drawable.ic_red_ball),
        Item("Jalebi", R.drawable.ic_jalebi),
        Item("Agarbatti", R.drawable.ic_agarbatti),
        Item("Ice", R.drawable.ic_ice),
        Item("Speaker", R.drawable.ic_speaker)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStructureSevenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        question = arguments?.getParcelable(StructureSixFragment.TAG) ?: Question()
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        setDemoView(isDemo)
        val senses = listOf(
            binding.flSense1,
            binding.flSense2,
            binding.flSense3,
            binding.flSense4,
            binding.flSense5
        )

        val actions = listOf(
            binding.flAction1,
            binding.flAction2,
            binding.flAction3,
            binding.flAction4,
            binding.flAction5
        )

        senses.forEach { sense ->
            sense.setOnClickListener {
                if (!listTrueConnection.contains(sense.id.toString())) {
                    selectedSense?.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.background_rounded_border_green
                    )
                    sense.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.background_light_green_border_green
                    )
                    selectedSense = it
                }
            }
        }

        actions.forEach { action ->
            action.setOnClickListener {
                action.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_light_green_border_green
                )
                CoroutineScope(Dispatchers.Main).launch {
                    delay(100L)
                    selectedSense?.let { sense ->
                        if (isValidConnection(senses.indexOf(sense), actions.indexOf(action))) {
                            drawLine(sense, action, getColorSense(sense.id))
                            listTrueConnection.add(sense.id.toString())
                            getBackgroundSense(sense.id)?.let {
                                sense.background = it
                                action.background = it
                            }
                        } else {
                            sense.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.background_rounded_border_green
                            )
                            action.background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.background_rounded_border_green
                            )
                        }
                    }
                    selectedSense = null
                }

            }
        }
        setOptionView((question.quesCategory?.categoryName ?: "").contains("AAA"))
    }

    private fun isValidConnection(senseIndex: Int, actionIndex: Int): Boolean {
//        return senseIndex == actionIndex
        val isDemo = (question.quesCategory?.categoryName ?: "").contains("AAA")
        val optionSense = if (isDemo) {
            leftDemo
        } else {
            leftQues
        }
        val optionAction = if (isDemo) {
            rightDemo
        } else {
            rightQues
        }
        val selectedSenseAndAction =
            "${optionSense[senseIndex].valName}-${optionAction[actionIndex].valName}"
        Log.d("isValidConnection ", "$selectedSenseAndAction")
        question.question?.correctAnswer?.let {
            return it.contains(selectedSenseAndAction)
        }
        return false
    }

    private fun drawLine(start: View, end: View, color: Int) {
        val startX = start.x + start.width
        val startY = start.y + start.height / 2
        val endX = binding.rightColumn.x
        val endY = end.y + end.height / 2
        binding.lineDrawView.addLine(startX, startY, endX, endY, color)
    }

    private fun getColorSense(idSense: Int): Int {
        return if (idSense == R.id.flSense2) {
            ContextCompat.getColor(requireContext(), R.color.colorRed)
        } else if (idSense == R.id.flSense1) {
            ContextCompat.getColor(requireContext(), R.color.colorGreen)
        } else if (idSense == R.id.flSense3) {
            ContextCompat.getColor(requireContext(), R.color.colorOrange)
        } else if (idSense == R.id.flSense4) {
            ContextCompat.getColor(requireContext(), R.color.colorYellow)
        } else {
            ContextCompat.getColor(requireContext(), R.color.colorBlue)
        }
    }

    private fun getBackgroundSense(idSense: Int): Drawable? {
        return when (idSense) {
            R.id.flSense2 -> {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_ligth_red_border_red
                )
            }

            R.id.flSense1 -> {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_light_green_border_green
                )
            }

            R.id.flSense3 -> {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_light_orange_border_orange
                )
            }

            R.id.flSense4 -> {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_light_yellow_border_yellow
                )
            }

            else -> {
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.background_light_blue_border_blue
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOptionView(isDemo: Boolean) {
        val optionSense = if (isDemo) {
            leftDemo
        } else {
            leftQues
        }
        val optionAction = if (isDemo) {
            rightDemo
        } else {
            rightQues
        }
        val listTextSense =
            listOf(
                binding.tvSense1,
                binding.tvSense2,
                binding.tvSense3,
                binding.tvSense4,
                binding.tvSense5
            )
        val listImageAction =
            listOf(
                binding.ivAction1,
                binding.ivAction2,
                binding.ivAction3,
                binding.ivAction4,
                binding.ivAction5
            )
        listTextSense.forEachIndexed { index, i ->
            i.text = optionSense[index].valName
        }
        listImageAction.forEachIndexed { index, iv ->
            Glide.with(requireContext())
                .load(optionAction[index].src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(iv)
        }
    }
    fun setDemoView(isDemo : Boolean){
        binding.tvDemo.visibility=View.VISIBLE
        if(isDemo){
            binding.tvDemo.text="Demo"
        }else{
            binding.tvDemo.text="Skip"
            binding.tvDemo.setOnClickListener {
                (requireActivity() as CognitiveActivity).setUserAnswerTheQuestion()
                (requireActivity() as CognitiveActivity).goToNextScreen()
            }
        }
    }
}