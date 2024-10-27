package com.anganwadi.app.ui.fragment

import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.BaseFragment
import com.anganwadi.app.DragCallback
//import com.anganwadi.app.DragHelper
import com.anganwadi.app.ItemAdapter
import com.anganwadi.app.R
import com.anganwadi.app.databinding.FragmentCognitiveTaskSecondBinding
import com.anganwadi.app.databinding.FragmentCognitiveTastThirdBinding

class CognitiveTaskSecondFragment : BaseFragment() {
    private var _binding: FragmentCognitiveTaskSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter1: ItemAdapter
    private lateinit var adapter2: ItemAdapter
    private var draggedItem: String? = null
    private var dragSourceAdapter: ItemAdapter? = null
    private lateinit var itemTouchHelper: ItemTouchHelper
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
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        // Initialize lists with sample data
        val list1 = mutableListOf("Item 1", "Item 2", "Item 3")
        val list2 = mutableListOf("Item 4", "Item 5", "Item 6")

        // Setup first RecyclerView
        adapter1 = ItemAdapter(list1)
        binding.recyclerView1.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapter1
            setDragListener(adapter1)
        }

        // Setup second RecyclerView
        adapter2 = ItemAdapter(list2)
        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapter2
            setDragListener(adapter2)
        }

        setupDropZones()
    }

    private fun RecyclerView.setDragListener(adapter: ItemAdapter) {
        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder?.let {
                        viewHolder.itemView.alpha = 0.5f
                        dragSourceAdapter = adapter
                        draggedItem = (viewHolder as ItemAdapter.ItemViewHolder)
                            .binding.textView.text.toString()
                    }
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.alpha = 1.0f
            }
        })
        touchHelper.attachToRecyclerView(this)
    }

    private fun setupDropZones() {
        binding.recyclerView1.setOnDragListener { view, event ->
            handleDrag(event, adapter1)
        }

        binding.recyclerView2.setOnDragListener { view, event ->
            handleDrag(event, adapter2)
        }
    }

    private fun handleDrag(event: DragEvent, targetAdapter: ItemAdapter): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                draggedItem?.let { item ->
                    if (dragSourceAdapter != targetAdapter) {
                        dragSourceAdapter?.let { sourceAdapter ->
                            // Find and remove item from source adapter
                            val position = findItemPosition(sourceAdapter, item)
                            if (position != -1) {
                                sourceAdapter.removeItem(position)
                                targetAdapter.addItem(item)
                            }
                        }
                    }
                }
                draggedItem = null
                dragSourceAdapter = null
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                draggedItem = null
                dragSourceAdapter = null
                return true
            }
            else -> return true
        }
    }

    private fun findItemPosition(adapter: ItemAdapter, item: String): Int {
        for (i in 0 until adapter.itemCount) {
            val viewHolder = binding.recyclerView1.findViewHolderForAdapterPosition(i)
                    as? ItemAdapter.ItemViewHolder
            if (viewHolder?.binding?.textView?.text.toString() == item) {
                return i
            }
        }
        return -1
    }
}