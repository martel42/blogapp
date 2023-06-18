package com.example.sasastapplicaton.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Post

class BlogSubsDiff: DiffUtil.ItemCallback<BlogSubs>() {
    override fun areItemsTheSame(oldItem: BlogSubs, newItem: BlogSubs): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BlogSubs, newItem: BlogSubs): Boolean {
        return oldItem == newItem
    }
}