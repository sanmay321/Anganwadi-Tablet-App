package com.anganwadi.app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anganwadi.app.databinding.RvItemQuestionBinding

class QuestionAdapter(
    private var context: Context,
    private val itemList: List<Char>
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: RvItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle1.text= itemList[position].toString()
    }

    override fun getItemCount(): Int = itemList.size
}