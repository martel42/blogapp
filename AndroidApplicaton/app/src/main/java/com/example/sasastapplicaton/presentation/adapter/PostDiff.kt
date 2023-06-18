package com.example.sasastapplicaton.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sasastapplicaton.data.db.entity.Post

class PostDiff: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}