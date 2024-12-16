package com.anganwadi.app.ui.fragment

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.anganwadi.app.databinding.FragmentTreeBinding
import com.anganwadi.app.model.Question

class StructureFragmentTree : Fragment() {
    private var _binding: FragmentTreeBinding? = null
    private val binding get() = _binding!!
    private lateinit var question: Question
    private var correctAnswer: String=""

    companion object {
        const val TAG = "tag"
        fun newFragment(question: Question): StructureFragmentTree {
            return StructureFragmentTree().apply {
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
        _binding = FragmentTreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up drag listeners on the targets
        binding.cldropTarget1.setOnDragListener(dragListener)
        binding.cldropTarget2.setOnDragListener(dragListener)

        // Set up drag actions on the draggable items
        setupDraggable(binding.clGrandma, "Grandma")
        setupDraggable(binding.clGrandpa, "Grandpa")
        setupDraggable(binding.clDad, "Dad")
        setupDraggable(binding.clMom, "Mom")
        setupDraggable(binding.clSon, "Son")
    }
    // Set up the draggable view
    private fun setupDraggable(view: ConstraintLayout, label: String) {
        view.setOnLongClickListener {
            val clipData = ClipData.newPlainText("family_member", label)
            val dragShadow = View.DragShadowBuilder(view)
            view.startDragAndDrop(clipData, dragShadow, view, 0) // Pass the view as local state
            true
        }
    }

    // Drag listener to handle drop events
    private val dragListener = View.OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription?.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) == true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
//                v.setBackgroundResource(R.drawable.circle_green_border_green) // Highlight on entry
                true
            }

            DragEvent.ACTION_DRAG_EXITED -> {
//                v.setBackgroundResource(R.drawable.circle_white_border_gray) // Reset on exit
                true
            }

            DragEvent.ACTION_DROP -> {
                val draggedView = event.localState as? ConstraintLayout
                if (draggedView != null) {
                    val draggedImage = draggedView.getChildAt(0) as ImageView // Get dragged ImageView
                    val draggedDrawable = draggedImage.drawable

                    // Set the target's ImageView to the dragged ImageView's drawable
                    val targetImageView = (v as ConstraintLayout).getChildAt(0) as ImageView
//                    targetImageView.setImageDrawable(draggedDrawable)

                    // Set matching feedback using the TextView
                    val targetTextView = v.getChildAt(1) as TextView
                    val draggedLabel = event.clipData.getItemAt(0).text.toString()

                    if (targetTextView.text == draggedLabel) {
                        targetImageView.setImageDrawable(draggedDrawable)
                        Toast.makeText(requireContext(), "Correct match: $draggedLabel", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Wrong match! Try again.", Toast.LENGTH_SHORT).show()
                    }
                    true
                } else {
                    false
                }
            }

            DragEvent.ACTION_DRAG_ENDED -> {
//                v.setBackgroundResource(R.drawable.circle_white_border_gray) // Reset background
                true
            }

            else -> false
        }
    }

}