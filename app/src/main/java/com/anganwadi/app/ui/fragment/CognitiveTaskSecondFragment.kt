package com.anganwadi.app.ui.fragment

import android.media.Image
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.DraggableItemAdapter
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskSecondBinding
import com.anganwadi.app.model.ImageModel

class CognitiveTaskSecondFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskSecondBinding? = null
    private val binding get() = _binding!!
    private var imageItems : ArrayList<ImageModel> = ArrayList()
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
        imageItems = arrayListOf(
            ImageModel(0,R.drawable.ic_cognitive_task_question),
            ImageModel(1,R.drawable.ic_star),
            ImageModel(2,R.drawable.ic_circle),
            ImageModel(3,R.drawable.ic_triangle),
            ImageModel(4,R.drawable.ic_cognitive_task_option4),
        )


        binding.recyclerViewItems.layoutManager =
            GridLayoutManager(requireActivity(), 2)

        binding.recyclerDropped1.layoutManager =
            GridLayoutManager(requireActivity(), 5)
        binding.recyclerDropped2.layoutManager =
            GridLayoutManager(requireActivity(), 5)
        binding.recyclerDropped3.layoutManager =
            GridLayoutManager(requireActivity(), 5)

        binding.recyclerViewItems.adapter = DraggableItemAdapter(imageItems)

        binding.recyclerDropped1.adapter = DraggableItemAdapter(arrayListOf())
        binding.recyclerDropped2.adapter = DraggableItemAdapter(arrayListOf())
        binding.recyclerDropped3.adapter = DraggableItemAdapter(arrayListOf())


        setDropTarget(binding.recyclerDropped1)
        setDropTarget(binding.recyclerDropped2)
        setDropTarget(binding.recyclerDropped3)
    }

    private fun setDropTarget(dropTarget: RecyclerView) {
        dropTarget.setOnDragListener { _, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> true

                DragEvent.ACTION_DROP -> {
                    val id = event.clipData?.getItemAt(0)?.text.toString().toInt()
                    val adapter =dropTarget.adapter as DraggableItemAdapter
                    val index= imageItems.map{it.id}.indexOf(id)
                    adapter.addItem(imageItems[index])
                    val masterAdapter=binding.recyclerViewItems.adapter as DraggableItemAdapter
                    masterAdapter.removeItem(imageItems[index])
                    true
                }


                else -> false
            }
        }
    }
}