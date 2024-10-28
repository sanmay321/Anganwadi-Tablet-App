package com.anganwadi.app

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.model.ImageModel

class ImageItemAdapter(
    private var context: Context,
    private val itemList: ArrayList<ImageModel>, // List of drawable resource IDs
    private val isPotrait: Boolean
) : RecyclerView.Adapter<ImageItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewItem)
    }

    fun addItem(image: ImageModel) {
        itemList.add(image)
        notifyDataSetChanged()
    }

    fun removeItem(image: ImageModel) {
        itemList.remove(image)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_master_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val size = if (isPotrait) {
            context.resources.getDimensionPixelSize(R.dimen.size_100)
        } else {
            context.resources.getDimensionPixelSize(R.dimen.size_120)
        }
        holder.itemView.layoutParams.height =size
        holder.itemView.layoutParams.width =size
        holder.itemView.requestLayout()
        holder.imageView.setImageResource(itemList[position].image)

        // Set item as draggable
        holder.imageView.setOnLongClickListener {
            val clipData = ClipData.newPlainText("", itemList[position].id.toString())
            val dragShadow = View.DragShadowBuilder(it)
            it.startDragAndDrop(clipData, dragShadow, it, 0)
            true
        }
    }

    override fun getItemCount(): Int = itemList.size
}
