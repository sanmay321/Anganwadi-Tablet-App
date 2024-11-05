package com.anganwadi.app.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentPhysicalDevelopmentTaskSecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhysicalDevelopmentTaskSecondFragment: BaseFragment() {
    private var _binding: FragmentPhysicalDevelopmentTaskSecondBinding? = null
    private val binding get() = _binding!!
    private var selectedSense: View? = null
    private var listTrueConnection: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhysicalDevelopmentTaskSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val senses = listOf(
            binding.flSkin,
            binding.flEyes,
            binding.flEar,
            binding.flNose,
            binding.flTongue
        )

        val actions = listOf(
            binding.flSkinAction,
            binding.flEyesAction,
            binding.flEarAction,
            binding.flNoseAction,
            binding.flTongueAction
        )

        senses.forEach { sense ->
            sense.setOnClickListener {
                if (!listTrueConnection.contains(sense.id.toString())) {
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
                            // Show error message
                            Toast.makeText(requireContext(),
                                "Invalid connection",
                                Toast.LENGTH_SHORT
                            ).show()
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

    }

    private fun isValidConnection(senseIndex: Int, actionIndex: Int): Boolean {
        return senseIndex == actionIndex
    }

    private fun drawLine(start: View, end: View, color: Int) {
        val startX = start.x + start.width
        val startY = start.y + start.height / 2
        val endX = end.x
        val endY = end.y + end.height / 2
        binding.lineDrawView.addLine(startX, startY, endX, endY, color)
    }

    private fun getColorSense(idSense: Int): Int {
        return if (idSense == R.id.flEyes) {
            ContextCompat.getColor(requireContext(), R.color.colorRed)
        } else if (idSense == R.id.flSkin) {
            ContextCompat.getColor(requireContext(), R.color.colorGreen)
        } else if (idSense == R.id.flEar) {
            ContextCompat.getColor(requireContext(), R.color.colorOrange)
        } else if (idSense == R.id.flNose) {
            ContextCompat.getColor(requireContext(), R.color.colorYellow)
        } else {
            ContextCompat.getColor(requireContext(), R.color.colorBlue)
        }
    }
    private fun getBackgroundSense(idSense: Int): Drawable? {
        return when (idSense) {
            R.id.flEyes -> {
                ContextCompat.getDrawable(requireContext(), R.drawable.background_ligth_red_border_red)
            }
            R.id.flSkin -> {
                ContextCompat.getDrawable(requireContext(), R.drawable.background_light_green_border_green)
            }
            R.id.flEar -> {
                ContextCompat.getDrawable(requireContext(), R.drawable.background_light_orange_border_orange)
            }
            R.id.flNose -> {
                ContextCompat.getDrawable(requireContext(), R.drawable.background_light_yellow_border_yellow)
            }
            else -> {
                ContextCompat.getDrawable(requireContext(), R.drawable.background_light_blue_border_blue)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}