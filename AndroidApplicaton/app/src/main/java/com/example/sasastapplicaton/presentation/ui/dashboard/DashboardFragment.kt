package com.example.sasastapplicaton.presentation.ui.dashboard

import PostListAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sasastapplicaton.data.network.jwt.Provider
import com.example.sasastapplicaton.databinding.FragmentDashboardBinding
import com.example.sasastapplicaton.databinding.FragmentNotificationsBinding
import com.example.sasastapplicaton.presentation.activity.CreatePostActivity
import com.example.sasastapplicaton.presentation.activity.MainActivity

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var postListAdapter: PostListAdapter
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        setupRecyclerView()


        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.postList.observe(viewLifecycleOwner) {
            val a = it.filter { it.blogId == Provider.getInstanceBlogUID() }
            postListAdapter.submitList(a)
        }

        binding.buttonAddPost.setOnClickListener {
            val intent = Intent(context, CreatePostActivity::class.java)
            startActivity(intent)
            //activity?.finish()
        }
    }


    private fun setupRecyclerView() {
        with(binding.rvPostList) {
            postListAdapter = PostListAdapter()
            adapter = postListAdapter
            recycledViewPool.setMaxRecycledViews(
                30,
                20
            )
        }
        setupClickListener()
        setupSwipeListener(binding.rvPostList)
    }

    //Удаление по свайпу
    private fun setupSwipeListener(postList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = postListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePost(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(postList)
    }


    private fun setupClickListener() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}