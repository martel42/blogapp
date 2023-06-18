package com.example.sasastapplicaton.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Post

class BlogSubsListDiff (
    private val oldList: List<BlogSubs>,
    private val newList: List<BlogSubs>,
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

}