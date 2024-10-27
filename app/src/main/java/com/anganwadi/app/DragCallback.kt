package com.anganwadi.app

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DragCallback(
    private val recyclerView1: RecyclerView,
    private val recyclerView2: RecyclerView,
    private val adapter1: ItemAdapter,
    private val adapter2: ItemAdapter
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                viewHolder?.itemView?.alpha = 0.5f
            }
            ItemTouchHelper.ACTION_STATE_IDLE -> {
                viewHolder?.let {
                    handleDrop(it)
                }
            }
        }
    }

    private fun handleDrop(viewHolder: RecyclerView.ViewHolder) {
        val fromPosition = viewHolder.adapterPosition
        if (fromPosition == -1) return

        val location = IntArray(2)
        viewHolder.itemView.getLocationInWindow(location)
        val itemCenterX = location[0] + viewHolder.itemView.width / 2
        val itemCenterY = location[1] + viewHolder.itemView.height / 2

        recyclerView1.getLocationInWindow(location)
        val rv1Left = location[0]
        val rv1Right = rv1Left + recyclerView1.width
        val rv1Top = location[1]
        val rv1Bottom = rv1Top + recyclerView1.height

        recyclerView2.getLocationInWindow(location)
        val rv2Left = location[0]
        val rv2Right = rv2Left + recyclerView2.width
        val rv2Top = location[1]
        val rv2Bottom = rv2Top + recyclerView2.height

        // Check which RecyclerView the item is currently in and where it should go
        val sourceAdapter: ItemAdapter
        val targetAdapter: ItemAdapter
        val targetRecyclerView: RecyclerView

        if (viewHolder.itemView.parent == recyclerView1) {
            sourceAdapter = adapter1
            targetAdapter = adapter2
            targetRecyclerView = recyclerView2
        } else {
            sourceAdapter = adapter2
            targetAdapter = adapter1
            targetRecyclerView = recyclerView1
        }

        // Check if the item is over the target RecyclerView
        val isOverTarget = when (targetRecyclerView) {
            recyclerView1 -> itemCenterX in rv1Left..rv1Right && itemCenterY in rv1Top..rv1Bottom
            else -> itemCenterX in rv2Left..rv2Right && itemCenterY in rv2Top..rv2Bottom
        }

        if (isOverTarget) {
            val item = sourceAdapter.removeItem(fromPosition)
            targetAdapter.addItem(0, item)
        }

        viewHolder.itemView.alpha = 1.0f
    }

    override fun canDropOver(
        recyclerView: RecyclerView,
        current: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }
}