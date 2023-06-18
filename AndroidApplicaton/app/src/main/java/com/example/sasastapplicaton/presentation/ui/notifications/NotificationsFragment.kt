package com.example.sasastapplicaton.presentation.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sasastapplicaton.data.db.entity.BlogSubs
import com.example.sasastapplicaton.data.db.entity.Subs
import com.example.sasastapplicaton.data.network.jwt.Provider
import com.example.sasastapplicaton.databinding.FragmentDashboardBinding
import com.example.sasastapplicaton.databinding.FragmentNotificationsBinding
import com.example.sasastapplicaton.presentation.activity.CreatePostActivity
import com.example.sasastapplicaton.presentation.adapter.BlogSubsListAdapter
import com.example.sasastapplicaton.presentation.ui.dashboard.DashboardViewModel

class NotificationsFragment : Fragment()  {

    private lateinit var viewModel: NotificationsViewModel
    private lateinit var blogSubsListAdapter: BlogSubsListAdapter
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        setupRecyclerView()
        viewModel.listBlog.observe(viewLifecycleOwner) {
            val a = it.filter { it.id != Provider.getInstanceBlogUID() }
            viewModel.listSubs.observe(viewLifecycleOwner) {
                    val b: List<Subs>? = it?.filter { it.userUid == Provider.userUID }
                    var c = listOf<BlogSubs>()
                    if(b == null)
                        c = a.map { BlogSubs(it.id, it.id, it.nameBlog, false) }
                    else
                        c = a.map { BlogSubs(it.id, it.id, it.nameBlog, b.map { it.blogId }.contains(it.id)) }
                blogSubsListAdapter.submitList(c)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setupRecyclerView() {
        with(binding.rvPostList) {
            blogSubsListAdapter = BlogSubsListAdapter()
            adapter = blogSubsListAdapter
            recycledViewPool.setMaxRecycledViews(
                30,
                20
            )
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        blogSubsListAdapter.onBlogSubsClickListener = {
            println("fragment!")
            it.isPodpis = !it.isPodpis
            println(it.isPodpis)
            viewModel.checked(it)
        }

    }

}