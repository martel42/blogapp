package com.example.sasastapplicaton.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sasastapplicaton.R
import com.example.sasastapplicaton.data.db.database.AppDatabase
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.databinding.ItemBlogBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient

class BlogSubsListAdapter: ListAdapter<BlogSubs, BlogSubsViewHolder>(BlogSubsDiff()) {
    var onBlogSubsClickListener: ((BlogSubs) -> Unit)? = null

    val gson = Gson()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogSubsViewHolder {
        val layout = R.layout.item_blog
        val binding = ItemBlogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BlogSubsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogSubsViewHolder, position: Int) {
        val blog = getItem(position)
        val binding = holder.binding

        with(binding) {
            with(blog) {
                blogListName.text = blog.blogDes
                isPodpiska.isChecked = blog.isPodpis
            }
        }

        binding.root.setOnClickListener {
            onBlogSubsClickListener?.invoke(blog)
            binding.isPodpiska.isChecked = blog.isPodpis
            //blog.isPodpis = !blog.isPodpis
            println("adapter")

        }
    }

}