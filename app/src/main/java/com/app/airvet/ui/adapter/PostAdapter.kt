package com.app.airvet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.airvet.databinding.ItemPostBinding
import com.app.airvet.model.UserModel


class PostAdapter(
    private val onItemClick: (UserModel) -> Unit
) :
    ListAdapter<UserModel, PostAdapter.ResultsViewHolder>(
        object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    class ResultsViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: UserModel,
            index: Int,
            onItemClick: (UserModel) -> Unit
        ) {
            binding.apply {
                user = item
                binding.parent.setOnClickListener { onItemClick(item) }
                binding.image.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflator, parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position), position, onItemClick)
    }

}