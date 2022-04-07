package com.udb.taller2DSA.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udb.taller2DSA.databinding.RecordItemBinding
import com.udb.taller2DSA.model.RecordModel

class RecordAdapter : ListAdapter<RecordModel, RecordAdapter.ItemViewHolder>(DiffCallback) {


    class ItemViewHolder(private var binding: RecordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecordModel) {
            binding.order = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<RecordModel>() {
        override fun areItemsTheSame(oldItem: RecordModel, newItem: RecordModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RecordModel, newItem: RecordModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(RecordItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}