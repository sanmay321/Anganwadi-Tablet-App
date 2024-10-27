//package com.anganwadi.app
//
//import android.view.View
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//
//class DragHelper(
//    private val sourceList: RecyclerView,
//    private val targetList: RecyclerView,
//    private val sourceAdapter: ItemAdapter,
//    private val targetAdapter: ItemAdapter
//) : ItemTouchHelper.Callback() {
//
//    private var dragFrom: Int = -1
//    private var dragTo: Int = -1
//    private var currentAdapter: ItemAdapter? = null
//    private var isMovingBetweenLists = false
//
//    override fun getMovementFlags(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder
//    ): Int {
//        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or
//                ItemTouchHelper.START or ItemTouchHelper.END
//        return makeMovementFlags(dragFlags, 0)
//    }
//
//    override fun onMove(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        target: RecyclerView.ViewHolder
//    ): Boolean {
//        val fromPosition = viewHolder.adapterPosition
//        val toPosition = target.adapterPosition
//
//        if (dragFrom == -1) {
//            dragFrom = fromPosition
//            currentAdapter = if (recyclerView == sourceList) sourceAdapter else targetAdapter
//        }
//        dragTo = toPosition
//
//        if (!isMovingBetweenLists) {
//            currentAdapter?.moveItem(fromPosition, toPosition)
//        }
//
//        return true
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        // Not implementing swipe
//    }
//
//    override fun chooseDropTarget(
//        selected: RecyclerView.ViewHolder,
//        dropTargets: MutableList<RecyclerView.ViewHolder>,
//        curX: Int,
//        curY: Int
//    ): RecyclerView.ViewHolder? {
//        val selectedItemView = selected.itemView
//        val location = IntArray(2)
//        selectedItemView.getLocationOnScreen(location)
//
//        val centerX = location[0] + selectedItemView.width / 2
//        val centerY = location[1] + selectedItemView.height / 2
//
//        if (isViewUnder(targetList, centerX, centerY) && currentAdapter == sourceAdapter) {
//            isMovingBetweenLists = true
//            moveItemBetweenLists(selected.adapterPosition)
//            return null
//        } else if (isViewUnder(sourceList, centerX, centerY) && currentAdapter == targetAdapter) {
//            isMovingBetweenLists = true
//            moveItemBetweenLists(selected.adapterPosition)
//            return null
//        }
//
//        isMovingBetweenLists = false
//        return super.chooseDropTarget(selected, dropTargets, curX, curY)
//    }
//
//    private fun moveItemBetweenLists(position: Int) {
//        val sourceAdapter = currentAdapter ?: return
//        val targetAdapter = if (sourceAdapter == this.sourceAdapter) this.targetAdapter else this.sourceAdapter
//
//        val item = sourceAdapter.removeItem(position)
//        targetAdapter.addItem(targetAdapter.itemCount, item)
//
//        // Reset drag state
//        dragFrom = -1
//        dragTo = -1
//        currentAdapter = targetAdapter
//    }
//
//    private fun isViewUnder(view: View, x: Int, y: Int): Boolean {
//        val location = IntArray(2)
//        view.getLocationOnScreen(location)
//        val viewX = location[0]
//        val viewY = location[1]
//        return (x >= viewX && x <= viewX + view.width &&
//                y >= viewY && y <= viewY + view.height)
//    }
//
//    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
//        super.clearView(recyclerView, viewHolder)
//        dragFrom = -1
//        dragTo = -1
//        currentAdapter = null
//        isMovingBetweenLists = false
//
//        // Reset view appearance
//        viewHolder.itemView.alpha = 1.0f
//        viewHolder.itemView.elevation = 0f
//    }
//
//    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
//        super.onSelectedChanged(viewHolder, actionState)
//
//        // Add visual feedback when item is being dragged
//        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
//            viewHolder?.itemView?.apply {
//                alpha = 0.7f
//                elevation = 8f
//            }
//        }
//    }
//}