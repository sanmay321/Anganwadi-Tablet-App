package com.anganwadi.app.ui.fragment

import android.content.res.Configuration
import android.media.Image
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.DraggableItemAdapter
import com.anganwadi.app.ImageItemAdapter
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskSecondBinding
import com.anganwadi.app.model.ImageModel

class CognitiveTaskSecondFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskSecondBinding? = null
    private val binding get() = _binding!!
    private var imageItems: ArrayList<ImageModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCognitiveTaskSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val orientation = resources.configuration.orientation
        pageForMax5()


        binding.recyclerViewItems.adapter = ImageItemAdapter(
            requireContext(),
            imageItems,
            orientation == Configuration.ORIENTATION_PORTRAIT
        )

        binding.recyclerDropped1.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)
        binding.recyclerDropped2.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)
        binding.recyclerDropped3.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)
        binding.recyclerDropped4.adapter = DraggableItemAdapter(requireContext(),arrayListOf(), orientation == Configuration.ORIENTATION_PORTRAIT)


        setDropTarget(binding.rlDropped1, binding.recyclerDropped1)
        setDropTarget(binding.rlDropped2, binding.recyclerDropped2)
        setDropTarget(binding.rlDropped3, binding.recyclerDropped3)
        setDropTarget(binding.rlDropped4, binding.recyclerDropped4)
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

    private fun pageForMax4() {
        imageItems = arrayListOf(
            ImageModel(0, R.drawable.ic_mango),
            ImageModel(1, R.drawable.ic_dog),
            ImageModel(2, R.drawable.ic_apple),
            ImageModel(3, R.drawable.ic_cat),
            ImageModel(4, R.drawable.ic_banana),
        )
        binding.rlDropped3.hideView()
        binding.rlDropped4.hideView()
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 3)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 3)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 2)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 3)
        }
    }
    private fun pageForMax5() {
        imageItems = arrayListOf(
            ImageModel(0, R.drawable.ic_pen),
            ImageModel(1, R.drawable.ic_spoon),
            ImageModel(2, R.drawable.ic_papaya),
            ImageModel(3, R.drawable.ic_dog),
            ImageModel(4, R.drawable.ic_knife),
            ImageModel(5, R.drawable.ic_cow),
            ImageModel(6, R.drawable.ic_lion),
            ImageModel(7, R.drawable.ic_mango),
            ImageModel(8, R.drawable.ic_orange),
            ImageModel(9, R.drawable.ic_cat),
            ImageModel(10, R.drawable.ic_apple),
            ImageModel(11, R.drawable.ic_banana),
            ImageModel(12, R.drawable.ic_comb),
        )
        binding.rlDropped4.hideView()
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped3.layoutManager =
                GridLayoutManager(requireActivity(), 5)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 2)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerDropped3.layoutManager =
                GridLayoutManager(requireActivity(), 4)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 7)
        }
    }
    private fun pageForMax6() {
        imageItems = arrayListOf(
            ImageModel(0, R.drawable.ic_lion),
            ImageModel(1, R.drawable.ic_bicycle),
            ImageModel(2, R.drawable.ic_parrot),
            ImageModel(3, R.drawable.ic_sheep),
            ImageModel(4, R.drawable.ic_orange),
            ImageModel(5, R.drawable.ic_scissors),
            ImageModel(6, R.drawable.ic_pineapple),
            ImageModel(7, R.drawable.ic_spoon),
            ImageModel(8, R.drawable.ic_car),
            ImageModel(9, R.drawable.ic_papaya),
            ImageModel(10, R.drawable.ic_knife),
            ImageModel(11, R.drawable.ic_bus),
            ImageModel(12, R.drawable.ic_hammer),
            ImageModel(13, R.drawable.ic_dog),
            ImageModel(14, R.drawable.ic_airoplane),
            ImageModel(15, R.drawable.ic_apple),
        )

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped3.layoutManager =
                GridLayoutManager(requireActivity(), 5)
            binding.recyclerDropped4.layoutManager =
                GridLayoutManager(requireActivity(), 5)

            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 2)
        } else {
            binding.recyclerDropped1.layoutManager =
                GridLayoutManager(requireActivity(), 6)
            binding.recyclerDropped2.layoutManager =
                GridLayoutManager(requireActivity(), 6)
            binding.recyclerDropped3.layoutManager =
                GridLayoutManager(requireActivity(), 6)
            binding.recyclerDropped4.layoutManager =
                GridLayoutManager(requireActivity(), 6)
            binding.recyclerViewItems.layoutManager =
                GridLayoutManager(requireActivity(), 8)
        }

    }
}