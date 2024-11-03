package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.DraggableItemAdapter
import com.anganwadi.app.ImageItemAdapter
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskFifthBinding
import com.anganwadi.app.model.ImageModel

class CognitiveTaskFifthFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskFifthBinding? = null
    private val binding get() = _binding!!
    private var imageItems: ArrayList<ImageModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCognitiveTaskFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val orientation = resources.configuration.orientation
        page1()

        binding.recyclerViewItems.adapter = ImageItemAdapter(
            requireContext(),
            imageItems,
            orientation == Configuration.ORIENTATION_PORTRAIT
        )

        binding.recyclerDropped1.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)
        binding.recyclerDropped2.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)


        setDropTarget(binding.rlDropped1, binding.recyclerDropped1)
        setDropTarget(binding.rlDropped2, binding.recyclerDropped2)
    }

    private fun setDropTarget(dropTarget: RelativeLayout, rvDropTarget: RecyclerView) {
        dropTarget.setOnDragListener { _, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> true

                DragEvent.ACTION_DROP -> {
                    dropTarget.setBackgroundResource(R.drawable.background_8_orange)
                    val id = event.clipData?.getItemAt(0)?.text.toString().toInt()
                    val adapter = rvDropTarget.adapter as DraggableItemAdapter
                    val index = imageItems.map { it.id }.indexOf(id)
                    adapter.addItem(imageItems[index])
                    val masterAdapter = binding.recyclerViewItems.adapter as ImageItemAdapter
                    masterAdapter.removeItem(imageItems[index])
                    true
                }


                else -> false
            }
        }
    }

    private fun page1() {
        imageItems = arrayListOf(
            ImageModel(0, R.drawable.ic_big_duck),
            ImageModel(1, R.drawable.ic_small_duck),
            ImageModel(2, R.drawable.ic_big_duck),
            ImageModel(3, R.drawable.ic_small_duck),
            ImageModel(4, R.drawable.ic_big_duck),
            ImageModel(5, R.drawable.ic_small_duck),
            ImageModel(6, R.drawable.ic_big_duck),
            ImageModel(7, R.drawable.ic_small_duck),
            ImageModel(8, R.drawable.ic_big_duck),
        )
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 3)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 3)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 3)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 5)
        }
    }
    private fun page2() {
        imageItems = arrayListOf(
            ImageModel(0, R.drawable.ic_watermelon),
            ImageModel(1, R.drawable.ic_feather),
            ImageModel(2, R.drawable.ic_potted_plant),
            ImageModel(3, R.drawable.ic_chair),
            ImageModel(4, R.drawable.ic_rock),
            ImageModel(5, R.drawable.ic_ball),
            ImageModel(6, R.drawable.ic_leaves),
            ImageModel(7, R.drawable.ic_jar),
        )
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 3)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 5)
        }
    }
}